package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

@WebServlet(urlPatterns = "/usucontroller")
public class UsuarioController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> lista = new ArrayList<>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturando oq vem do client/navegador
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		//instanciando obj		
		Usuario usu = new Usuario(nome,email);
		//inserinfo na lista
		
				lista.add(usu);
			
		
		
		resp.getWriter().println("{ nome:" + usu.getNome() + "  , email: " + usu.getEmail() + "  } ");
		
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//deletar por nome+email
		/*String nome = req.getParameter("nome");
		String email = req.getParameter("email");		
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getEmail().equals(email) && lista.get(i).getNome().equals(nome) ){
				lista.remove(i);
				i = lista.size();
			}
		}*/
		
		//deletar por indice
		int indice = Integer.parseInt(req.getParameter("indice"));		
		lista.remove(indice);		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int indice = Integer.parseInt(req.getParameter("indice"));
		
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		if(nome != "" && email.equals("")){
			lista.get(indice).setNome(nome);
		}else if(nome.equals("") && email != ""){
			lista.get(indice).setEmail(email);
		}else if(nome != "" && email != ""){
			lista.get(indice).setNome(nome);
			lista.get(indice).setEmail(email);
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = "[";
		for (int i = 0; i < lista.size(); i++) {

			json += "{ nome:" + lista.get(i).getNome() + "  , email: " + lista.get(i).getEmail() + "  }";
			if (i < lista.size()-1)
				json += ",";
		}
		json += "]";

		resp.getWriter().print(json);
	}

}
