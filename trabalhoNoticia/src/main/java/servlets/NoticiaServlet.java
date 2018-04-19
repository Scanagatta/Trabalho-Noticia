package servlets;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.client.MongoCollection;

import dao.ConexaoMongo;
import modelo.Noticia;

/**
 * Servlet implementation class NoticiaServlet
 */
@WebServlet("/NoticiaServlet")
public class NoticiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticiaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MongoCollection<Noticia> noticias = ConexaoMongo.db().getCollection("noticia", Noticia.class);

		String titulo = request.getParameter("titulo");
		String texto = request.getParameter("texto");

		Noticia noticiaNova = new Noticia();
		noticiaNova.setTitulo(titulo);
		noticiaNova.setTexto(texto);

		// add no banco
		noticias.insertOne(noticiaNova);

		// fazer update
		noticias.updateOne(eq("name", "Ada Byron"), combine(set("age", 23), set("name", "Ada Lovelace")));

		response.sendRedirect("noticia");
	}

}