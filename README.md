# TP JPA+SERVLET+REST R�alis� par Meriem Machnache & Lydia Moussa

 Le but de ce tp est de cr�er une application web en JPA et Servlet.
 cette application nous permet de creer et visualiser des personnes.

 Ce TP comprend 3 parties:
 Les classes m�tiers : attributs + getters + setters + annotations.
 La parie Servlet : Comprendre les m�canismes de servlet.
 La partie REST(GET): Comprendre les principes d'une architecture Rest.

# Modification des fichiers de configuration
  - Nous avons modifi� le fichier de configuration pom.xml
    en ajoutant une d�pendance � l'API des servlets.
  
  - Nous avons ajout� le plugin TomCat qui permet de d�marrez tomcat depuis maven
    en executant tomcat7:run dans le goal.
  
  - Nous avons modifi� le fichier de configuration persistence.xml
    pour adapter les informations � la base de donn�es MySQL


 
# Cr�ation de Servlet 
La premiere servlet Myservlet est pour afficher le fameux message Hello world SIR.
les deux autres servlets sont UserInfo et HomeInfo.

Servlet HomeInfo , nous avons deux m�thodes :
Doget: pour r�cup�rer les informations.
Dopost: pour cr�er une maison.
						 
La requete suivante nous permet de r�cup�rer la liste des maisons existantes, le r�sultat de 
la requete sera stock� dans la collection result de type Home.


Le code suivant permet de r�cup�rer la liste des maisons:

	 Collection<Home> result = manager.createQuery("Select h From Home h", 				Home.class).getResultList();
    out.println("<HTML>\n<BODY>\n" + "<H1>R�cup�ration des maisons</H1>\n" + "<UL>\n");
	 for (Home h : result) {
		out.println("<LI> maison :" + h+ "\n");	
		}
	 out.println("</UL>\n" + "</BODY></HTML>");
 
 Le code suivant permet de cr�er une maison:
   
		EntityManager manager = this.Manager.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Home home = new Home();
		home.setNombre_de_piece(Long.valueOf(request.getParameter("piece")));
		home.setTaille(Long.valueOf(request.getParameter("taille")));
		manager.persist(home);
		tx.commit();
	 
 
# R�cup�rer les donn�es en utilisant les servlets
 ce formulaire nous permet de visualiser les informations  de Home.
 Nous avons trois attributs: nombre de pieces, taille, personne.
 Nous avons pass� le nom de la servlet HomeInfo dans l'action du formulaire
 et nous avons pr�cis� le type de la m�thode get pour visualiser les donn�es.
 Pour cr�er une Home nous utilisons le type Post.

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
 
# Peuplement de la Base de donn�es 

  La classe JpaTest.java
  pour alimenter la base de donn�es.
 
  
 	   
# Partie Rest
La classe SampleWebService.java
	La m�thode Get permet de r�cup�rer les informations d'une home.
	
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
Nous avons d�fini les diff�rentes m�thodes qui permettent de faire les op�rations suivantes:
 - Lister l'ensemble des maisons.
 - Cr�er une nouvelle maison.
 - Modifier une maison.
 - Supprimer une maison.
 
    - Cette m�thode permet de r�cup�rer toutes les maisons sous format Json.
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getAllHomes() {
		.....
	}
	- Cette m�thode permet de rechercher une maison par son ID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHomeById(@PathParam("id") long id) {
		.....
	}

	- Cette m�thode permet de supprimer une maison par son ID
	 @DELETE
	 @Path("/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public void removeHomeById(@PathParam("id") long id ) {
	 .....
	 }

	- Cette m�thode permet de cr�er une maison 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHome(Home h) {
		.....
	}
  
  
 




