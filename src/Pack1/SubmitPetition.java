package Pack1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmitPetition
 */
@WebServlet("/SubmitPetition")
public class SubmitPetition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitPetition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String category=request.getParameterValues("type_of_petition")[0];
		String title=request.getParameterValues("textarea")[0];
		String description=request.getParameterValues("textarea")[1];
		String target=request.getParameterValues("target")[0];
		String tags=request.getParameterValues("taguri_petitie")[0];
		String name=request.getParameterValues("firstname")[0];
		String email=request.getParameterValues("email")[0];
		
			//System.out.println(category+" "+title+" "+description+" "+target+" "+tags+" "+name+" "+email);
			long trg=Long.parseLong(target);
			ArrayList<String> tags2=new ArrayList<String>();
			String tag;
			while(tags!=null)
			{
				tag=SubmitPetition.extractWord(tags);
				tags2.add(tag);
				if(tags.equalsIgnoreCase((SubmitPetition.skipWhiteSpaces(tags.substring(tag.length())))))
					tags=null;
				else
					tags=SubmitPetition.skipWhiteSpaces(tags.substring(tag.length()));
				
			}
			
			Petition p=new Petition(category,title,description,trg,0,tags2,name,email);
			p.getTags();
			Database.add(p);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static String skipWhiteSpaces(String request)
    {
    	int i=0;
    	if(request.length()==0)
    		return null;
    	while(request.charAt(i)==' ')
    		i++;
    	return request.substring(i);
    			
    }
    
   static String extractWord(String request)
    {
    	request=skipWhiteSpaces(request);
    	for(int i=0;i<request.length();i++)
    		if(request.charAt(i)==' ')
    			return request.substring(0,i);
    	return request;
    }

}
