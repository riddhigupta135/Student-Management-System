package studentmanagement.web;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagement.dao.BatchDAO;
import studentmanagement.dao.StudentDAO;
import studentmanagement.model.Batch;
import studentmanagement.model.Student;

/* This class gets commands from JSP and accordingly calls methods to get/change information 
 * by using getters/setters and user action/input. It also calls DAO classes if some changes 
 * need to be made permanent in the database. 
 */
@WebServlet("/")
public class TeacherAssistantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    private BatchDAO batchDAO;
    
    //Store information from MySQL into HashMaps to reduce the number of calls to database
    private HashMap<Integer, Student> studentMap;
    private HashMap<Integer, Batch> batchMap;
    private List<Integer> batchIds;
    private List<Integer> studentIds;
    
    

       
    /**
     * @see HttpServlet#HttpServlet()
     */

	
	public void init() {
		studentDAO = new StudentDAO();
		batchDAO = new BatchDAO();
		studentMap = new HashMap<Integer, Student>();
		batchMap = new HashMap<Integer, Batch>();
		batchIds = new ArrayList<Integer>();
		studentIds = new ArrayList<Integer>();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();

		/*These cases inside try switch are called by the .jsp files 
		 * to determine which method to execute.
		 */
		try {
			switch (action) {
			case "/newStudent":
				showNewStudentForm(request, response);
				break;
			case "/insertStudent":
				insertStudent(request, response);
				break;
			case "/deleteStudent":
				deleteStudent(request, response);
				break;
			case "/editStudent":
				showEditStudentForm(request, response);
				break;
			case "/updateStudent":
				updateStudent(request, response);
				break;
			case "/takeAttendance":
				takeAttendance(request, response);
				break;
			case "/studentsWithFeeDue":
				studentsWithFeeDue(request, response);
				break;
			case "/updateStudentWithFeeDue":
				updateStudentWithFeeDue(request, response);
				break;
			case "/oneBatchDisplay":
				oneBatchDisplay(request, response);
				break;
			case "/newBatch":
				showNewBatchForm(request, response);
				break;
			case "/insertBatch":
				insertBatch(request, response);
				break;
			case "/deleteBatch":
				deleteBatch(request, response);
				break;
			case "/editBatch":
				showEditBatchForm(request, response);
				break;
			case "/updateBatch":
				updateBatch(request, response);
				break;
			case "/listBatch":
				listBatch(request, response);
				break;
			case "/studentsInBatchDisplay":
				studentsInBatchDisplay(request, response);
				break;
			case "/previousStudents":
				previousStudents(request, response);
				break;
			case "/restoreStudent":
				restoreStudent(request, response);
				break;
			case "/previousBatches":
				previousBatches(request, response);
				break;
			case "/restoreBatch":
				restoreBatch(request, response);
				break;
			case "/listCalendar":
				listCalendar(request, response);
				break;
				
			default:
				listStudent(request, response);
				break;
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();

			throw new ServletException(ex);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		}
	
	//clears the existing HashMap and accesses the data base to get the new values
	private void refreshMaps()
	{
		List<Student> allStudents = StudentDAO.selectAllStudents();
		List<Batch> allBatches = BatchDAO.selectAllBatches();
		studentMap.clear();
		studentIds.clear();
		batchMap.clear();
		batchIds.clear();
		
		for(Student s : allStudents)
		{
			studentMap.put(s.getStudentId(), s);
			studentIds.add(s.getStudentId());
		}
		
		for(Batch b : allBatches)
		{
			batchMap.put(b.getBatchId(), b);
			batchIds.add(b.getBatchId());
		}
	}

		
		
		//List all of the students that are not deleted
		private void listStudent(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException { //dataBase call saved (uses only HashMap unless accessed for the 1st time)
			
			if(studentMap.size()==0 || batchMap.size()==0 )
			{
				refreshMaps();
			}
			List<Student> listStudent = new ArrayList<Student>();
			for(Student s: studentMap.values())
			{
				if(!s.getIsDeleted())
				{
					listStudent.add(s);
				}
			}
			
			//to sort students in ascending order by their batch id. 
			Comparator<Student> sortStudentByBatchId = new Comparator<Student>() 
			{
				public int compare(Student one, Student two)
				{
					return one.getBatchNumber() - two.getBatchNumber();
				}
			};
			
			Collections.sort(listStudent, sortStudentByBatchId);
			
			//send this information to studentList.jsp
			request.setAttribute("listStudent", listStudent);
			request.setAttribute("pageName",1); //For setting header of studentList.jsp
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
			dispatcher.forward(request, response);
		}

		
		//display a form to add new student
		private void showNewStudentForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException { //dataBase call saved
			
			//send batchIds (from the List) to studentList.jsp
			request.setAttribute("batchIds", batchIds);
			RequestDispatcher dispatcher = request.getRequestDispatcher("newStudentForm.jsp");
			dispatcher.forward(request, response);
		}

		//add a new student to the database
		private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			
			//access all of the information of the students
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			boolean isFeeDue =  Boolean.parseBoolean(request.getParameter("isFeeDue"));
			int feeDueAfter = Integer.parseInt(request.getParameter("feeDueAfter"));
			int batchNumber = Integer.parseInt(request.getParameter("batchNumber"));
			boolean isDeleted =  false; //Since the student is being added, it is not deleted

			Student newStudent = new Student(name, email, phone, address, batchNumber, isFeeDue, feeDueAfter, isDeleted);
			
			studentDAO.insertStudent(newStudent); //call to DAO to access the MYSQL database
			refreshMaps(); //refresh the HashMap to reflect the changes
			
			response.sendRedirect("listStudent"); //send the users to list student page
		}
		
		
		//show the form that enables user to edit student; displays current student values
		private void showEditStudentForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException { //dataBase calls saved
			int id = Integer.parseInt(request.getParameter("studentId"));
			
			Student existingStudent = studentMap.get(id);

			request.setAttribute("batchIds", batchIds);
			RequestDispatcher dispatcher = request.getRequestDispatcher("editStudentForm.jsp");
			request.setAttribute("student", existingStudent);
			dispatcher.forward(request, response);
		}

		
		//update feeDueAfter and possible isFeeDue
		private void takeAttendance(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException { //dataBase calls saved
			int id = Integer.parseInt(request.getParameter("studentId"));
			Student existingStudent = studentMap.get(id);

			int feeDueAfter = Integer.parseInt(request.getParameter("feeDueAfter"));
			boolean isFeeDue = existingStudent.getIsFeeDue();
			
			/*if the current feeDueAfter is 1, that means that once attendance is clicked, the
			 * students fee has become due. So, feeDueAfter will be reset to 8, while isFeeDue will be
			 * set to true so that the student is listed in Students with Fee Due tab.
			 */
			if(feeDueAfter==1)
			{
				feeDueAfter = 8;
				isFeeDue = true;
			}
			else
			{
				feeDueAfter--;
			}

			studentDAO.updateStudentAttendance(id, feeDueAfter, isFeeDue);
			refreshMaps();
			response.sendRedirect("listStudent");
		}
		
		
		//change student's information in the database
		private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			
			//collect all of the student information
			int id = Integer.parseInt(request.getParameter("studentId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			boolean isFeeDue =  Boolean.parseBoolean(request.getParameter("isFeeDue"));
			int feeDueAfter = Integer.parseInt(request.getParameter("feeDueAfter"));
			int batchNumber = Integer.parseInt(request.getParameter("batchNumber"));
			boolean isDeleted =  false;
			
			Student editStudent = new Student(id, name, email, phone, address, batchNumber, isFeeDue, feeDueAfter, isDeleted);
			
			//update the new information in the database
			studentDAO.updateStudent(editStudent);
			//refresh the HashMap to include these changes
			refreshMaps();
			
			response.sendRedirect("listStudent");
		}

		
		//deletes the student (NOT from the database, only from display)
		private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("studentId"));
			studentDAO.deleteStudent(id);
			refreshMaps();
			response.sendRedirect("listStudent");
		}
		
		//Bring back a deleted student (if that action was done on accident)
		private void restoreStudent(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("studentId"));
			studentDAO.restoreStudent(id);
			refreshMaps();
			response.sendRedirect("previousStudents");
		}
		
		//displays students whose fee has not been paid
		private void studentsWithFeeDue(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			List<Student> listStudent = new ArrayList<Student>();
			for(Student s: studentMap.values())
			{
				if(s.getIsFeeDue())
				{
					listStudent.add(s);
				}
			}
			request.setAttribute("listStudent", listStudent);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentsWithFeeDue.jsp");
			dispatcher.forward(request, response);
		}
		
		//refreshes the database with changes in isFeeDue variable
		private void updateStudentWithFeeDue(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("studentId"));
			studentDAO.updateIsFeeDue(id);
			refreshMaps();
			response.sendRedirect("studentsWithFeeDue");
		}
		
		
		//displays the students that were previously deleted
		private void previousStudents(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {

			List<Student> listStudent = new ArrayList<Student>();
			for(Student s: studentMap.values())
			{
				if(s.getIsDeleted())
				{
					listStudent.add(s);
				}
			}
			request.setAttribute("listStudent", listStudent);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("previousStudentList.jsp");
			dispatcher.forward(request, response);
		}

		//**************************Batch Methods***************************************
		
		//List out the details of all existing batches
		private void listBatch(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			
			if(batchMap.size()==0)
			{
				refreshMaps();
			}
			
			List<Batch> listBatch = new ArrayList<Batch>();
			
			for(Batch b: batchMap.values())
			{
				if(!b.getIsDeleted())
				{
					listBatch.add(b);
				}
			}
			
			request.setAttribute("listBatch", listBatch);
			request.setAttribute("pageName",1); //For setting header of studentList.jsp
						
			RequestDispatcher dispatcher = request.getRequestDispatcher("batchList.jsp");
			dispatcher.forward(request, response);
		}

		//Display a form that will ask for information about the new batch
		private void showNewBatchForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("newBatchForm.jsp");
			dispatcher.forward(request, response);
		}


		//add the new batch to the database
		private void insertBatch(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			String day = request.getParameter("day");
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			Time startTime = new Time (formatter.parse(startTimeStr).getTime());
			Time endTime = new Time (formatter.parse(endTimeStr).getTime());
			String homework = request.getParameter("homework");
			int level = Integer.parseInt(request.getParameter("level"));
			String lessonPlan = request.getParameter("lessonPlan");
			boolean isDeleted =  Boolean.parseBoolean(request.getParameter("isDeleted"));
			Batch newBatch = new Batch(day, startTime, endTime, homework, level, lessonPlan, isDeleted);
			
			batchDAO.insertBatch(newBatch);
			refreshMaps();
			response.sendRedirect("listBatch");
		}
		
		
		//show the form to edit a batch; displays current information on the form
		private void showEditBatchForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("batchId"));
			Batch existingBatch = batchMap.get(id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("editBatchForm.jsp");
			request.setAttribute("batch", existingBatch);
			dispatcher.forward(request, response);
		}
		
		
		//update the information on batch on the database
		private void updateBatch(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			int id = Integer.parseInt(request.getParameter("batchId"));
			String day = request.getParameter("day");
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			Time startTime = new Time (formatter.parse(startTimeStr).getTime());
			Time endTime = new Time (formatter.parse(endTimeStr).getTime());
			String homework = request.getParameter("homework");
			int level = Integer.parseInt(request.getParameter("level"));
			String lessonPlan = request.getParameter("lessonPlan");
			boolean isDeleted =  Boolean.parseBoolean(request.getParameter("isDeleted"));
			
			Batch editBatch = new Batch(id, day, startTime, endTime, homework, level, lessonPlan, isDeleted);
			batchDAO.updateBatch(editBatch);
			refreshMaps();
			response.sendRedirect("listBatch");
			
		}

		//deletes a batch (only from the list, not the database)
		private void deleteBatch(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("batchId"));
			batchDAO.deleteBatch(id);
			refreshMaps();
			response.sendRedirect("listBatch");
		}
		
		
		//displays one specific batch
		private void oneBatchDisplay(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			int id = Integer.parseInt(request.getParameter("batchNumber"));
			
			Batch batch = batchMap.get(id);
			List<Batch> listBatch = new ArrayList<Batch>();
			listBatch.add(batch);
			
			request.setAttribute("listBatch", listBatch);
			request.setAttribute("pageName", 2);
			request.setAttribute("batchNum",id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("batchList.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//display all of the students that are a part of a particular batch
		private void studentsInBatchDisplay(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			int id = Integer.parseInt(request.getParameter("batchId"));
									
			List<Student> listStudent = new ArrayList<Student>();
			for(Student s: studentMap.values())
			{
				if(s.getBatchNumber()==id && !s.getIsDeleted())
				{
					listStudent.add(s);
				}
			}
			
			request.setAttribute("listStudent", listStudent);
			request.setAttribute("pageName",2); //For setting header of studentList.jsp
			request.setAttribute("batchNum",id);
			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//list out the batches that were previously deleted
		private void previousBatches(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException { //dataBase call saved

			List<Batch> listBatch = new ArrayList<Batch>();
			for(Batch b: batchMap.values())
			{
				if(b.getIsDeleted())
				{
					listBatch.add(b);
				}
			}
			request.setAttribute("listBatch", listBatch);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("previousBatchList.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//bring back a deleted batch
		private void restoreBatch(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("batchId"));
			batchDAO.restoreBatch(id);
			refreshMaps();
			response.sendRedirect("previousBatchList.jsp");

		}
		
		//list the calendar page with Monday to Friday display with times (in increasing order)
		private void listCalendar(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			
			if(batchMap.size()==0)
			{
				refreshMaps();
			}
			List<Batch> mondayBatch = new ArrayList<Batch>();
			List<Batch> tuesdayBatch = new ArrayList<Batch>();
			List<Batch> wednesdayBatch = new ArrayList<Batch>();
			List<Batch> thursdayBatch = new ArrayList<Batch>();
			List<Batch> fridayBatch = new ArrayList<Batch>();
			for(Batch b: batchMap.values())
			{
				if(!b.getIsDeleted())
				{
					if(b.getDay().equals("Monday"))
					{
						mondayBatch.add(b);
					}
					else if(b.getDay().equals("Tuesday"))
					{
						tuesdayBatch.add(b);
					}
					else if(b.getDay().equals("Wednesday"))
					{
						wednesdayBatch.add(b);
					}
					else if(b.getDay().equals("Thursday"))
					{
						thursdayBatch.add(b);
					}
					else if(b.getDay().equals("Friday"))
					{
						fridayBatch.add(b);
					}
				}
			}
			
			//For sorting the batches based on the time on their specific day
			Comparator<Batch> batchSortByTime = new Comparator<Batch>() 
			{
				public int compare(Batch one, Batch two)
				{
					return one.getStartTime().compareTo(two.getStartTime());
				}
			};
			
			Collections.sort(mondayBatch, batchSortByTime);
			Collections.sort(tuesdayBatch, batchSortByTime);
			Collections.sort(wednesdayBatch, batchSortByTime);
			Collections.sort(thursdayBatch, batchSortByTime);
			Collections.sort(fridayBatch, batchSortByTime);
			
			request.setAttribute("mondayBatch", mondayBatch);
			request.setAttribute("tuesdayBatch", tuesdayBatch);
			request.setAttribute("wednesdayBatch", wednesdayBatch);
			request.setAttribute("thursdayBatch", thursdayBatch);
			request.setAttribute("fridayBatch", fridayBatch);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("calendarList.jsp");
			dispatcher.forward(request, response);
		}
		
}
