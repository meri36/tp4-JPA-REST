# TP JPA+SERVLET+REST Réalisé par Meriem Machnache & Lydia Moussa

 Le but de ce tp est de créer une application web en JPA et Servlet.
 cette application nous permet de creer et visualiser des personnes.

 Ce TP comprend 3 parties:
 Les classes métiers : attributs + getters + setters + annotations.
 La parie Servlet : Comprendre les mécanismes de servlet.
 La partie REST(GET): Comprendre les principes d'une architecture Rest.

# Modification des fichiers de configuration
  - Nous avons modifié le fichier de configuration pom.xml
    en ajoutant une dépendance à  l'API des servlets.
  
  - Nous avons ajouté le plugin TomCat qui permet de démarrez tomcat depuis maven
    en executant tomcat7:run dans le goal.
  
  - Nous avons modifié le fichier de configuration persistence.xml
    pour adapter les informations à la base de données MySQL


 
# Création de Servlet 
La premiere servlet Myservlet est pour afficher le fameux message Hello world SIR.
les deux autres servlets sont UserInfo et HomeInfo.

Servlet HomeInfo , nous avons deux méthodes :
Doget: pour récupérer les informations.
Dopost: pour créer une maison.
						 
La requete suivante nous permet de récupérer la liste des maisons existantes, le résultat de 
la requete sera stocké dans la collection result de type Home.


Le code suivant permet de récupérer la liste des maisons:

	 Collection<Home> result = manager.createQuery("Select h From Home h", 				Home.class).getResultList();
    out.println("<HTML>\n<BODY>\n" + "<H1>Récupération des maisons</H1>\n" + "<UL>\n");
	 for (Home h : result) {
		out.println("<LI> maison :" + h+ "\n");	
		}
	 out.println("</UL>\n" + "</BODY></HTML>");
 
 Le code suivant permet de créer une maison:
   
		EntityManager manager = this.Manager.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Home home = new Home();
		home.setNombre_de_piece(Long.valueOf(request.getParameter("piece")));
		home.setTaille(Long.valueOf(request.getParameter("taille")));
		manager.persist(home);
		tx.commit();
	 
 
# Récupérer les données en utilisant les servlets
 ce formulaire nous permet de visualiser les informations  de Home.
 Nous avons trois attributs: nombre de pieces, taille, personne.
 Nous avons passé le nom de la servlet HomeInfo dans l'action du formulaire
 et nous avons précisé le type de la méthode get pour visualiser les données.
 Pour créer une Home nous utilisons le type Post.

	<body>
	<FORM Method="POST" Action="/HomeInfo" Name="Form1">
		piece: <INPUT type=text size=20 name=piece><BR>  
		taille: <INPUT type=float size=20 name=taille><BR>
		personne : <INPUT type=text size=2 name=person><BR> 
		<INPUT type=submit value=Envoyer>
	</FORM>
	<FORM Method="GET" Action="/HomeInfo">
		<INPUT type=submit value=afficher>
	</FORM>
	</body>
 
# Peuplement de la Base de données 

  La classe JpaTest.java
  pour alimenter la base de données.
 
  
 	   
# Partie Rest
La classe SampleWebService.java
	La méthode Get permet de récupérer les informations d'une home.
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello, how are you?";
	}

	@GET
	@Path("/home")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHome() {
		Home home1 = new Home();
		home1.setTaille(30);
		Heater heater1 = new Heater();
		heater1.setPuissance(500);
		Heater heater2 = new Heater();
		heater2.setPuissance(700);
		Collection<Heater> heaters = new ArrayList();
		heaters.add(heater1);
		heaters.add(heater2);
		home1.setChauffage(heaters);

		return home1;
	}

La classe HomeService.java  
Nous avons défini les différentes méthodes qui permettent de faire les opérations suivantes:
 - Lister l'ensemble des maisons.
 - Créer une nouvelle maison.
 - Modifier une maison.
 - Supprimer une maison.
 
    - Cette méthode permet de récupérer toutes les maisons sous format Json.
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getAllHomes() {
		.....
	}
	- Cette méthode permet de rechercher une maison par son ID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHomeById(@PathParam("id") long id) {
		.....
	}

	- Cette méthode permet de supprimer une maison par son ID
	 @DELETE
	 @Path("/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public void removeHomeById(@PathParam("id") long id ) {
	 .....
	 }

	- Cette méthode permet de créer une maison 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHome(Home h) {
		.....
	}
  
  
 




