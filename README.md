# TP - SushiFast - Vincent ROURE & Mattéo BALDINETTI
## Contexte
Travaux pratiques réalisé en 3ème année de BUT Métiers du Multimédia et de l'Internet avec la matière Dev-Back.

## Technologies utilisées
- SpringBoot 3.5.8

## Installation
	# Cloner le projet et y accéder
	git clone https://github.com/MatteoBaldinetti/backend-tp-bibliotheque.git
	cd backend-tp-bibliothèque
	
	
## Arborescence
```
backend-tp-bibliotheque/
│
├── src/
│   ├── main/
│   │   ├── java/com/mmi/tp1/
│   │   │   ├── config/        # Contient les classes de configuration (ex : gestion de l’API Key)
│   │   │   ├── controller/    # Contrôleurs REST exposant les endpoints de l’API
│   │   │   ├── filter/        # Filtres appliqués aux requêtes HTTP (ex : ajout d’en-têtes)
│   │   │   ├── model/         # Classes modèles représentant les entités du projet
│   │   │   ├── repository/    # Interfaces JPA permettant l’accès à la base de données
│   │   │   ├── service/       # Logique métier utilisée par les contrôleurs
│   │   │   └── Tp1Application.java  # Classe principale lançant l’application Spring Boot
│   │   └── resources/
│   │       ├── application.properties  # Configuration de l’application (BDD, ports, etc.)
│   │       └── ...                     # Autres ressources utilisées par le projet
│
└── pom.xml      # Dépendances Maven et configuration du projet
```
	    
## Utilisation
Pour démarrer le serveur local, utilisez le logiciel Intellij Idea ou tout autre IDE et exécuter le fichier ```src/main/java/com/mmi/tp1/Tp1Application.java```.
