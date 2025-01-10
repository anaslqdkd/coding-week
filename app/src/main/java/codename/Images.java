package codename;

import codename.model.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

public class Images {

  // Declare arrays to hold the preloaded images
  public Image[] imagesRed = new Image[8];
  public Image[] imagesBlue = new Image[9];
  public Image[] imagesCivImages = new Image[6];

  private Game game;
  private int redCount;
  private int blueCount;

  // Lists to hold the random selected images
  public List<Image> selectedRedImages = new ArrayList<>();
  public List<Image> selectedBlueImages = new ArrayList<>();

  // Constructor to initialize and load images
  public Images() {
    this.game = Game.getInstance();
    redCount = this.game.getBoard().getRedCount();
    blueCount = this.game.getBoard().getBlueCount();

    // Load the images into the arrays
    for (int i = 0; i < imagesRed.length; i++) {
      imagesRed[i] = new Image("images/card_red_" + (i + 1) + ".png");
    }

    for (int i = 0; i < imagesBlue.length; i++) {
      imagesBlue[i] = new Image("images/card_blue_" + (i + 1) + ".png");
    }

    for (int i = 0; i < imagesCivImages.length; i++) {
      imagesCivImages[i] = new Image("images/card_civ_" + (i + 1) + ".png");
    }

    // Generate random red and blue images based on redCount and blueCount
    generateRandomImages();
  }

  // Method to generate the random selected images for red and blue
  private void generateRandomImages() {
    Random random = new Random();

    // Randomly select red images
    for (int i = 0; i < redCount; i++) {
      int randomIndex = random.nextInt(imagesRed.length); // Random index for red images
      selectedRedImages.add(imagesRed[randomIndex]); // Add selected image to the list
    }

    // Randomly select blue images
    for (int i = 0; i < blueCount; i++) {
      int randomIndex = random.nextInt(imagesBlue.length); // Random index for blue images
      selectedBlueImages.add(imagesBlue[randomIndex]); // Add selected image to the list
    }
  }

  public List<Image> getSelectedRedImages() {
    return selectedRedImages;
  }

  public List<Image> getSelectedBlueImages() {
    return selectedBlueImages;
  }
}
