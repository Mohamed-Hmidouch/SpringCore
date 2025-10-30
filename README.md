Absolument. Voici une documentation concise, allant à l'essentiel de chaque concept, comme si elle était rédigée par quelqu'un qui a assimilé ces idées.

---

## SPRING CORE — Inversion de Contrôle & Injection de Dépendances

### Qu’est-ce que Spring Core et à quoi sert-il dans une application Java ?

**Spring Core** est la fondation de tout le framework Spring. Son rôle principal est de gérer le cycle de vie des objets et la manière dont ils sont "câblés" (connectés) les uns aux autres. Il fait cela grâce à un conteneur qui implémente l'Inversion de Contrôle (IoC).

### Que signifie le principe d’Inversion de Contrôle (IoC) ?

C'est un principe de conception. Au lieu que votre objet (`Service A`) crée lui-même les objets dont il a besoin (`new ServiceB()`), vous **cédez ce contrôle** à un conteneur (Spring). C'est le conteneur qui crée et "injecte" le `ServiceB` dans le `ServiceA`.

### Quelle est la différence entre IoC et Injection de Dépendances (DI) ?

L'**IoC** est le **principe** (l'idée de céder le contrôle). L'**Injection de Dépendances (DI)** est le **mécanisme** (l'action, le "comment"). La DI est la manière la plus courante de réaliser l'IoC : le conteneur *injecte* les dépendances (objets) là où elles sont nécessaires (via un constructeur, un champ, ou un setter).

### Qu’est-ce qu’un bean dans Spring ?

C'est simplement un objet Java (une instance de classe) dont le cycle de vie est entièrement géré par le conteneur IoC de Spring.

### Quel est le rôle du conteneur IoC ?

C'est l'"usine" de votre application. Il lit votre configuration (qu'elle soit en XML, annotations ou Java), crée les beans, les assemble (injecte les dépendances) et gère leur cycle de vie complet.

### Quelle est la différence entre ApplicationContext et BeanFactory ?

Ce sont deux types de conteneurs IoC, mais :

* `BeanFactory` est l'interface de base, simple et "paresseuse" (elle ne crée un bean que lorsqu'on le demande).
* `ApplicationContext` est une extension (plus avancée) de `BeanFactory`. Elle est "proactive" (elle crée les beans *singleton* au démarrage) et ajoute plus de fonctionnalités (gestion des événements, AOP...). **On utilise presque toujours `ApplicationContext`**.

### Quelles sont les trois approches de configuration dans Spring ?

1.  **XML** : L'approche historique, où tous les beans et dépendances sont définis dans des fichiers XML.
2.  **Annotations** : Utiliser des annotations (`@Component`, `@Autowired`...) directement dans le code Java pour que Spring détecte les beans.
3.  **Java Config** : Utiliser des classes (`@Configuration`) et des méthodes (`@Bean`) Java pour définir explicitement les beans.

### À quoi servent les annotations suivantes :

* **`@Configuration`** : Signale à Spring qu'une classe contient des définitions de beans. C'est la base de la "Java Config".
* **`@ComponentScan`** : Dit à Spring : "Scanne ce package pour trouver des classes annotées (@Component, @Service...) et crée des beans pour elles."
* **`@Bean`** : Placée sur une méthode dans une classe `@Configuration`, elle dit à Spring que l'objet retourné par cette méthode est un bean à gérer.
* **`@Component`, `@Service`, `@Repository`, `@Controller`** : Ce sont des stéréotypes pour le `@ComponentScan`.
    * `@Component` est le stéréotype générique.
    * Les autres sont des spécialisations pour clarifier l'intention : `@Service` (logique métier), `@Repository` (accès aux données), `@Controller` (couche web).
* **`@Autowired`, `@Qualifier`** :
    * `@Autowired` est la demande d'injection. "Spring, trouve un bean de ce type et injecte-le ici."
    * `@Qualifier` est utilisé pour spécifier *quel* bean injecter s'il existe plusieurs beans du même type.

### Comment Spring détecte et crée automatiquement les composants ?

Grâce au **Component Scan** (activé par `@ComponentScan`). Il scanne les packages spécifiés et, pour chaque classe qu'il trouve marquée d'un stéréotype (`@Component`, `@Service`...), il crée un bean.

### Quelles sont les étapes du cycle de vie d’un bean ?

En bref :

1.  **Instanciation** (le `new`)
2.  **Injection** des dépendances (ex: champs `@Autowired`)
3.  **Initialisation** (appel des méthodes personnalisées, ex: `@PostConstruct`)
4.  Le bean est **prêt** et en service.
5.  **Destruction** (quand le conteneur s'arrête, appel des méthodes ex: `@PreDestroy`).

### Quelle est la différence entre les scopes de bean ?

Le "scope" (portée) définit combien d'instances de ce bean existeront :

* **`singleton`** (par défaut) : Une seule et unique instance pour toute l'application.
* **`prototype`** : Une nouvelle instance est créée *à chaque fois* que ce bean est demandé (injecté).
* Autres scopes (web) : `request` (un par requête HTTP), `session` (un par session utilisateur).

### Pourquoi la configuration manuelle (avant Spring Boot) est-elle importante à comprendre ?

Parce que Spring Boot n'est pas magique. **Spring Boot est simplement un "auto-configurateur" géant qui fait exactement ce travail manuel pour vous.** Comprendre la configuration manuelle, c'est comprendre ce que Boot fait sous le capot et comment le surcharger si nécessaire.

---

## SPRING DATA JPA — Persistance & Transactions

### Qu’est-ce que Spring Data JPA et quel problème résout-il ?

Spring Data JPA est une abstraction au-dessus de JPA. Son but est de **supprimer le code répétitif (boilerplate)** de la couche d'accès aux données.

Il le résout en vous demandant de créer seulement des **interfaces** (`UserRepository`). Spring Data se charge de générer automatiquement le code d'implémentation pour les opérations CRUD.

### Quelle est la différence entre JPA et Hibernate ?

* **JPA (Java Persistence API)** : C'est la **spécification** (le "contrat"). C'est un ensemble d'interfaces et d'annotations (`@Entity`, `@Id`...) définies par Java.
* **Hibernate** : C'est l'**implémentation** la plus populaire de JPA. C'est le "moteur" qui fait le travail en respectant le contrat JPA.

### Qu’est-ce qu’une entité JPA ?

C'est une simple classe Java (POJO) qui est "mappée" (liée) à une table de votre base de données. Elle doit être annotée avec `@Entity` et avoir une clé primaire `@Id`.

### À quoi sert le DataSource ?

C'est la "source de données". C'est un objet qui gère la connexion physique à la base de données. Dans une application moderne, il gère un **pool de connexions** (ex: HikariCP) pour réutiliser les connexions et améliorer les performances.

### Que fait l’EntityManager ?

C'est le cœur de JPA. C'est l'objet qui gère le "contexte de persistance". Il suit les changements de vos entités et exécute les opérations réelles sur la base de données (`persist`, `merge`, `find`, `remove`).

### Quelle est la responsabilité du TransactionManager ?

Il gère les transactions (le concept du "tout ou rien"). Son rôle est de s'assurer qu'une série d'opérations de base de données réussit entièrement (`COMMIT`) ou échoue entièrement (`ROLLBACK`), garantissant l'intégrité des données.

### À quoi sert l’annotation @EnableJpaRepositories ?

Similaire à `@ComponentScan`, elle dit à Spring Data JPA : "Scanne ce package pour trouver toutes les interfaces qui étendent `JpaRepository`, et génère automatiquement les implémentations pour elles."

### Qu’est-ce qu’un repository Spring Data ?

C'est une **interface** (ex: `interface UserRepository extends JpaRepository<User, Long>`) qui sert de contrat pour votre couche d'accès aux données. Vous n'écrivez pas la classe, Spring Data le fait pour vous.

### Quelles sont les méthodes génériques fournies par JpaRepository ?

`save(entity)`, `findById(id)`, `findAll()`, `delete(entity)`, `deleteById(id)`, `count()`, `existsById(id)`...

### Comment gérer les transactions avec Spring ?

La plupart du temps, en utilisant l'annotation **`@Transactional`**, généralement placée sur les méthodes de votre couche `@Service`. Spring s'occupe alors automatiquement d'ouvrir, de commiter ou de rollbacker la transaction.

### Pourquoi définir manuellement la connexion à la base de données avant Spring Boot ?

Parce qu'avant l'auto-configuration, Spring ne pouvait pas "deviner" vos informations de connexion. Vous deviez *explicitement* créer un bean `DataSource` et lui fournir le driver, l'URL, l'username et le mot de passe.

### Que doit contenir une configuration de persistance complète ?

Les "Trois Grands" de la configuration JPA manuelle :

1.  Un bean **`DataSource`** (la connexion).
2.  Un bean **`EntityManagerFactory`** (l'usine JPA, qui utilise le DataSource).
3.  Un bean **`TransactionManager`** (qui gère les transactions, en utilisant l'EntityManagerFactory).

### Qu’est-ce que la validation de contrainte dans le modèle ?

Ce sont les règles que vous appliquez à vos données.

* **Contraintes BDD (JPA)** : Ex: `@Column(nullable=false, unique=true)`. Elles sont appliquées au niveau de la base de données.
* **Contraintes de Validation (Bean Validation)** : Ex: `@Email`, `@Size(min=2)`. Elles sont vérifiées par l'application (souvent sur les DTOs) *avant* d'atteindre la BDD.

### Quelle est la différence entre une suppression logique (soft delete) et une suppression physique ?

* **Suppression Physique** (`DELETE FROM...`) : La ligne est **définitivement supprimée** de la table.
* **Suppression Logique (Soft Delete)** : La ligne n'est **jamais supprimée**. On utilise un `UPDATE` pour modifier un flag (ex: `isActive = false` ou `deletedAt = NOW()`). C'est utile pour l'historique et l'audit.

---

## SPRING MVC — Contrôleurs & Couche Web

### Que signifie MVC (Model-View-Controller) et quel est son objectif dans Spring ?

C'est un patron d'architecture qui sépare les responsabilités d'une application web :

* **Model** (Modèle) : Les données (ex: un `UserDto`).
* **View** (Vue) : La représentation de ces données (ex: une page HTML ou, pour une API REST, le **JSON**).
* **Controller** (Contrôleur) : Le "chef d'orchestre". Il reçoit la requête HTTP, demande les données au `Service`, et les envoie à la Vue.

Son objectif est de **séparer la logique métier de la présentation**.

### Quel est le rôle du DispatcherServlet dans Spring MVC ?

C'est le **contrôleur frontal**. Il est le point d'entrée unique de toutes les requêtes HTTP dans une application Spring MVC. C'est lui qui reçoit la requête et la "dispatche" (aiguille) vers le bon `@Controller` en fonction de l'URL.

### Quelle est la différence entre un Controller et un RestController ?

* **`@Controller`** (traditionnel) : Conçu pour renvoyer des *noms de vues* (ex: "user-list.jsp"). Le moteur de vue génère alors le HTML.
* **`@RestController`** (pour les API) : C'est un raccourci pour `@Controller` + `@ResponseBody`. Il dit à Spring que les méthodes renvoient directement les données (ex: un DTO), qui doivent être sérialisées (ex: en **JSON**), et non un nom de vue.

### Quelle est la fonction des annotations suivantes :

* **`@RequestMapping`** : L'annotation principale qui mappe une URL (ex: `/users`) et/ou une méthode HTTP à un contrôleur ou à une méthode de contrôleur.
* **`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`** : Ce sont des raccourcis pour `@RequestMapping` (ex: `@GetMapping("/users")` est identique à `@RequestMapping(path="/users", method=RequestMethod.GET)`).
* **`@Valid`** : Placé sur un paramètre de méthode (souvent un DTO), il demande à Spring d'activer la validation (Bean Validation) sur cet objet.
* **`@RequestBody`** et **`@PathVariable`** :
    * `@RequestBody` : Dit à Spring de prendre le **corps** de la requête HTTP (ex: un JSON) et de le convertir en objet Java (Désérialisation).
    * `@PathVariable` : Récupère une variable dynamique de l'**URL** (ex: l'ID dans `/users/{id}`).

### Comment le DispatcherServlet traite-t-il une requête HTTP du début à la fin ?

En bref :

1.  La requête HTTP arrive au `DispatcherServlet`.
2.  Il consulte le `HandlerMapping` (la "carte") pour trouver quelle méthode de `@Controller` correspond à l'URL.
3.  Il prépare les arguments de la méthode (ex: `@RequestBody`, `@PathVariable`).
4.  Il appelle la méthode du contrôleur.
5.  La méthode renvoie une réponse (ex: un DTO).
6.  Le `DispatcherServlet` prend cette réponse et la convertit (ex: en JSON) pour la renvoyer au client.

### Qu’est-ce que la classe de configuration Web (WebConfig) et à quoi sert-elle ?

C'est la classe `@Configuration` qui est spécifique à Spring MVC. C'est là que vous configurez le comportement de MVC, comme les convertisseurs JSON, la gestion des CORS, les "interceptors", etc.

### Pourquoi faut-il initialiser le DispatcherServlet manuellement avant Spring Boot ?

Parce que sans Spring Boot, votre application démarre dans un conteneur standard (comme Tomcat). Vous deviez *manuellement* dire à Tomcat d'utiliser le `DispatcherServlet` de Spring pour gérer les requêtes HTTP, soit via un fichier `web.xml`, soit via un `WebAppInitializer`.

### Qu’est-ce qu’un WebAppInitializer et pourquoi remplace-t-il web.xml ?

C'est l'approche moderne (100% Java) pour démarrer une application Spring MVC. C'est une classe qui **remplace l'ancien fichier `web.xml`**. Elle permet de configurer le `DispatcherServlet` et le `ContextLoaderListener` (qui charge la configuration Core/Data) en utilisant du code Java pur.

### Quelles sont les étapes de traitement d’une requête REST dans Spring MVC ?

1.  Le client envoie une requête JSON (ex: `POST /users`).
2.  Le `DispatcherServlet` la reçoit.
3.  Il la mappe au `@PostMapping` du `@RestController`.
4.  Jackson (ou une autre bibliothèque) **désérialise** le JSON en `CreateUserDto` (grâce à `@RequestBody`).
5.  Le `Controller` appelle le `Service` avec le DTO.
6.  Le `Service` fait son travail et renvoie un `ResponseUserDto`.
7.  Le `RestController` retourne ce DTO.
8.  Jackson **sérialise** le `ResponseUserDto` en JSON.
9.  Le `DispatcherServlet` envoie la réponse JSON au client.

### Comment se fait la sérialisation et désérialisation des objets JSON ?

Spring utilise une bibliothèque tierce, le plus souvent **Jackson**.

* **Désérialisation** : Jackson lit le JSON entrant et l'utilise pour créer une instance de votre classe Java (ex: DTO).
* **Sérialisation** : Jackson prend votre objet Java (ex: DTO) et le transforme en une chaîne de caractères JSON.

Tout cela se fait automatiquement grâce à `@RequestBody` et `@RestController`.

### À quoi sert un @RestControllerAdvice ?

C'est un "super-contrôleur" global. Son rôle principal est de **centraliser la gestion des exceptions** pour toute l'application. Il intercepte les exceptions lancées par n'importe quel `@Controller` et les transforme en une réponse JSON propre (ex: un statut 404 personnalisé).

### Quelles sont les bonnes pratiques pour organiser les packages d’un projet MVC ?

L'approche la plus courante est l'organisation **"par couche"** (ou "par type"). Cela permet de séparer clairement les responsabilités :

* `com.example.config` (pour les classes `@Configuration`)
* `com.example.controller` (pour les `@RestController`)
* `com.example.service` (pour les `@Service`)
* `com.example.repository` (pour les interfaces `JpaRepository`)
* `com.example.model` (ou `domain`, pour les entités `@Entity`)
* `com.example.dto` (pour les objets de transfert de données)
* `com.example.mapper` (pour les traducteurs DTO/Entité)