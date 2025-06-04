package moteurJeu;

//https://github.com/zarandok/megabounce/blob/master/MainCanvas.java

import gameLaby.LabyJeu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// copied from: https://gist.github.com/james-d/8327842
// and modified to use canvas drawing instead of shapes

public class MoteurJeu extends Application {

    /**
     * gestion du temps : nombre de frame par secondes et temps par iteration
     */
    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);

    /**
     * taille par defaut
     */
    private static double WIDTH = 800;
    private static double HEIGHT = 600;

    /**
     * statistiques sur les frames
     */
    private final FrameStats frameStats = new FrameStats();

    /**
     * jeu en Cours et renderer du jeu
     */
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;

    /**
     * touches appuyee entre deux frame
     */
    static Clavier controle = new Clavier();

    public static Stage primaryStageRef;

    private static final String[] informations = {
            "Vous pouvez vous déplacer à l'aide des touches Z,Q,S et D ou bien les flèches directionnelles" +
                    "Allez au niveau suivant en allant sur l'escalier en face et en appuyant sur la touche A",
            "Ces interrupteurs au sol permettent d'ouvrir les portes. Attention, si vous remarchez dessus, cela la ferme",
            "ATTENTION, UN MONSTRE !!" +
                    "Essayez de le battre en le regardant et en appuyant sur la barre d'espace",
            "Durant votre aventure, vous rencontrerez des pièges, même si ils sont invisibles au départ, il suffit de marcher " +
                    "dessus et ils vous seront visibles",
            "Maintenant essayez d'intéragir avec les objets, le bouclier vous protegera, l'épée permettra d'attaquer autour de vous et la potion " +
                    "vous soignera." + "\n Afin vous n'avez qu'à vous positionner sur un objet et appuyer sur la touche E de votre clavier pour intéragir. Pour utiliser la potion, appuyez également sur E en selectionnant " +
                    "l'objet avec les touches 1 à 4 de votre clavier (pas le pavé numérique)",
            "Mes conseils s'arrêtent ici, vous devez continuer votre chemin. Je vous préviens tout de même, plus vous avancerez et plus les monstres seront efficaces pour vous traquer..."
    };

    /**
     * attribut permettant de gérer la génération du labyrinthe
     */

    private class Auditeur implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            try {
                MoteurJeu.jeu = new LabyJeu("labySimple/" + b.getText());
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Problèmes dans la gestion des niveaux");
            }
        }
    }

    /**
     * lancement d'un jeu
     *
     * @param jeu    jeu a lancer
     * @param dessin dessin du jeu
     */
    public static void launch(Jeu jeu, DessinJeu dessin) {
        // le jeu en cours et son afficheur
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;

        // si le jeu existe, on lance le moteur de jeu
        if (jeu != null)
            launch();
    }

    /**
     * frame par secondes
     *
     * @param FPSSouhaitees nombre de frames par secondes souhaitees
     */
    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }

    //#################################
    // SURCHARGE Application
    //#################################

    @Override
    /**
     * creation de l'application avec juste un canvas et des statistiques
     */
    public void start(Stage primaryStage) {

        //on stock le stage de départ
        primaryStageRef = primaryStage;

        // initialisation du canvas de dessin et du container
        final Canvas canvas = new Canvas();
        final Pane canvasContainer = new Pane(canvas);
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());

        // affichage des stats
        final Label stats = new Label();
        stats.textProperty().bind(frameStats.textProperty());

        // ajout des statistiques en bas de la fenetre
        final BorderPane root = new BorderPane();
        root.setCenter(canvasContainer);
        root.setBottom(stats);

        // creation de la scene
        final Scene scene = new Scene(root, WIDTH, HEIGHT);

        //écran d'accueil
        Image backgroundImage = new Image("file:texture/background/test.png");
        ImageView bg1 = new ImageView(backgroundImage);
        ImageView bg2 = new ImageView(backgroundImage);
        bg1.setPreserveRatio(true);
        bg2.setPreserveRatio(true);
        bg1.setFitHeight(HEIGHT);
        bg2.setFitHeight(HEIGHT);

        // on positionne bg2 juste à droite de bg1
        double visibleWidth = bg1.getBoundsInParent().getWidth();
        bg2.setX(bg1.getX() + visibleWidth);

        //Bouton play
        Button play = new Button("Commencer");
        play.setLayoutX(WIDTH / 3 - 30);
        play.setLayoutY(HEIGHT / 2);
        play.setOnAction(e -> {
            primaryStage.setScene(scene);
            startAnimation(canvas);
        });

        //bouton de selection de niveau
        Button quit = new Button("Quitter");
        quit.setLayoutX((WIDTH * 2) / 3 - 30);
        quit.setLayoutY(HEIGHT / 2);
        quit.setOnAction(e -> {
            System.exit(0);
        });

        //titre
        Image title_img = new Image("file:texture/title/zeldiablo_title.png");
        ImageView title = new ImageView(title_img);
        title.setLayoutX(WIDTH / 4 - 20);
        title.setLayoutY(-50);

        Pane root2 = new Pane(bg1, bg2, play, title, quit);
        Scene scene2 = new Scene(root2, WIDTH, HEIGHT);

        //timer pour le gif qui tourne à l'"infini"
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // permet de faire glisser l'image vers la gauche
                bg1.setX(bg1.getX() - 0.5);
                bg2.setX(bg2.getX() - 0.5);

                // on remet à droite de la seconde image si l'image est totalement hors de l'écran
                if (bg1.getX() + bg1.getImage().getWidth() <= 0) {
                    bg1.setX(bg2.getX() + bg2.getImage().getWidth());
                }
                if (bg2.getX() + bg2.getImage().getWidth() <= 0) {
                    bg2.setX(bg1.getX() + bg1.getImage().getWidth());
                }
            }
        };
        timer.start();

        primaryStage.setScene(scene2);
        primaryStage.show();


        // listener clavier
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.appuyerTouche(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.relacherTouche(event);
            }
        });


        // creation du listener souris
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            jeu.init();
                        }
                    }
                });
    }
//
//    /**
//     * Methode permettant d'afficher le menu pour selectionner le niveau
//     */
//    private void afficherNiveaux() {
//        Dialog<String> dialog = new Dialog<>();
//        dialog.setTitle("test selection niveau");
//        dialog.setWidth(WIDTH / 2);
//        dialog.setHeight(HEIGHT / 2);
//
//        File dossLaby = new File("labySimple/");
//        File[] labys = dossLaby.listFiles();
//        ArrayList<String> nomLabys = new ArrayList<>();
//
//        for (File f : labys) {
//            nomLabys.add(f.getName());
//        }
//
//        GridPane choix = new GridPane();
//        choix.setHgap(10);
//        choix.setVgap(10);
//
//        for (int i = 1; i <= nomLabys.size(); i++) {
//            Label label = new Label("Niveau " + i);
//            Button button = new Button(nomLabys.get(i - 1));
//            Auditeur audi = new Auditeur();
//            button.addEventHandler(ActionEvent.ACTION, audi);
//            choix.add(label, 0, i);
//            choix.add(button, 1, i);
//        }
//        choix.setAlignment(Pos.CENTER);
//
//        dialog.getDialogPane().setContent(choix);
//
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
////        dialog.getDialogPane().setContent(test2);
//        dialog.showAndWait();
//    }

    /**
     * gestion de l'animation (boucle de jeu)
     *
     * @param canvas le canvas sur lequel on est synchronise
     */
    private void startAnimation(final Canvas canvas) {
        // stocke la derniere mise e jour
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        // timer pour boucle de jeu
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {

                // si jamais passe dans la boucle, initialise le temps
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                // mesure le temps ecoule depuis la derniere mise a jour
                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;


                // si le temps ecoule depasse le necessaire pour FPS souhaite
                if (dureeEnMilliSecondes > dureeFPS) {
                    // met a jour le jeu en passant les touches appuyees
                    jeu.update(dureeEnMilliSecondes / 1_000., controle);

                    // dessine le jeu
                    dessin.dessinerJeu(jeu, canvas);

                    // ajoute la duree dans les statistiques
                    frameStats.addFrame(elapsedTime);

                    // met a jour la date de derniere mise a jour
                    lastUpdateTime.set(timestamp);
                }

            }
        };

        // lance l'animation
        timer.start();
    }

    public static void AfficherTuto(int i) {
        Platform.runLater(() -> {
            controle.reset();
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Tuto n°" + i);
            dialog.setContentText(informations[i]);
            dialog.setWidth(WIDTH / 2);
            dialog.setHeight(HEIGHT / 2);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.showAndWait();
        });
    }

    public static void GameOver() {
        Platform.runLater(() -> {
            Stage gameOverStage = new Stage();
            gameOverStage.setTitle("Game Over");
            gameOverStage.initModality(Modality.APPLICATION_MODAL);

            File imgf_gameOver = new File("texture/gameOver.png");
            Image img_gameOver = new Image(imgf_gameOver.toURI().toString());
            ImageView background = new ImageView(img_gameOver);
            background.setPreserveRatio(false);
            background.setFitWidth(500);
            background.setFitHeight(300);

            Button quitBtn = new Button("Quitter");
            quitBtn.setOnAction(e -> System.exit(0));
            quitBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");

            // Utiliser une VBox pour centrer horizontalement et descendre verticalement
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(20);
            vbox.setPadding(new Insets(150, 0, 0, 0)); // décale vers le bas
            vbox.getChildren().add(quitBtn);

            StackPane root = new StackPane();
            root.getChildren().addAll(background, vbox);

            Scene scene = new Scene(root, 500, 300);
            gameOverStage.setScene(scene);
            gameOverStage.show();
        });
    }



}
