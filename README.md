# CodingWeek 2025
**TELECOM Nancy - 06/01 au 10/01**

# Application JavaFX - Codename

Cette application est un projet Java utilisant JavaFX pour l'interface graphique. Ce guide explique comment exécuter l'exécutable `.jar` généré avec Gradle.

---

## Prérequis

Avant d'exécuter l'application, assurez-vous d'avoir les éléments suivants installés :

1. **Java 21** ou une version compatible de la JVM.
   - Vérifiez la version de Java installée :
     ```bash
     java -version
     ```

2. **JavaFX SDK** version 23 ou ultérieure.
   - Téléchargez le SDK JavaFX depuis [Gluon](https://gluonhq.com/products/javafx/).
   - Assurez-vous de noter le chemin vers le dossier `lib` du SDK, qui sera nécessaire pour exécuter l'application.

3. **Gradle** (si vous souhaitez compiler vous-même).
   - Vous pouvez aussi utiliser `./gradlew` fourni avec le projet.

---

## Compilation et Génération du JAR

Si vous souhaitez recompiler le projet ou régénérer le JAR, procédez comme suit :

1. Clonez le projet :
   ```bash
   git clone <url-du-repo>
   cd <nom-du-repo>

2. Compiler le projet avec :
    '''./gradlew clean build

Le fichier se trouvera dans : 
    '''app/exec/codenames.jar
## Dans tout les cas : 

**Utilisez la commande suivante pour lancer l'exécution du .jar :**
java --module-path "path_to_javafx"/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics -jar app/exec/codenames.jar


