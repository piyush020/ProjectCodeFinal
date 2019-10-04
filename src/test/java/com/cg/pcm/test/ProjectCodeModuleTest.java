package com.cg.pcm.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.pcm.entity.Project;
import com.cg.pcm.exception.ProjectException;
import com.cg.pcm.repository.ProjectRepository;
import com.cg.pcm.service.ProjectServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectCodeModuleTest {

	static String pattern = "yyyy-MM-dd";
	static String startdate = "2019-07-10";
	static String enddate = "2019-08-11";
	static java.util.Date startDate = null;
	static java.sql.Date sqlStartDate = null;
	static java.util.Date endDate = null;
	static java.sql.Date sqlEndDate = null;

	@Test
	public void contextLoads() {

	}

	@Mock
	ProjectRepository repository;

	@InjectMocks
	ProjectServiceImpl projectCtrl;

	@Spy
	List<Project> list = new ArrayList<Project>();

	@Captor
	ArgumentCaptor<Project> captor;

	@BeforeClass
	public static void runThis() throws ParseException {
		DateFormat formatter = new SimpleDateFormat(pattern);

		startDate = formatter.parse(startdate);
		sqlStartDate = new java.sql.Date(startDate.getTime());
		endDate = formatter.parse(enddate);
		sqlEndDate = new java.sql.Date(endDate.getTime());
	}

	@Before
	public void setup() throws ParseException {
		MockitoAnnotations.initMocks(this);

		list.add(new Project(101L, "Project_101", "Project_101_30days", sqlStartDate, sqlEndDate, "BU_123"));
		list.add(new Project(102L, "Project_102", "Project_102_30days", sqlStartDate, sqlEndDate, "BU_125"));

	}

	@Test
	public void addProject() throws ParseException, ProjectException {

		// create mock
		Project pro = new Project(101L, "Project_101", "Project_101_30days", sqlStartDate, sqlEndDate, "BU_123");

		// define return value for method save()
		when(repository.save(pro)).thenReturn(pro);
		assertEquals(projectCtrl.addProject(pro), pro);
	}

	@Test
	public void updateProject() throws ParseException, ProjectException {

		// create mock
		Project pro = new Project(101L, "Project_101", "Project_101_30days", sqlStartDate, sqlEndDate, "BU_123");
		// define return value for method save()
		when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.of(pro));
		Assert.assertEquals(pro, projectCtrl.getProjectById(101L));
		when(repository.save(any(Project.class))).thenReturn(pro);
		pro.setBusinessUnit("BU_400");
		;
		projectCtrl.updateProject(pro);
		verify(repository, times(1)).save(captor.capture());
		assertEquals(captor.getValue().getId(), 101L);
		Assert.assertEquals(2, list.size());
		verify(list, times(2)).add(any(Project.class));
	}

	@Test
	public void searchProject() throws ProjectException, ParseException {

		Project pro = new Project(101L, "Project_101", "Project_101_30days", sqlStartDate, sqlEndDate, "BU_123");
		when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.of(pro));
		Assert.assertEquals(pro, projectCtrl.getProjectById(101L));
		Assert.assertEquals(2, list.size());
		verify(list, times(2)).add(any(Project.class));
	}

	@Test
	public void viewProjects() throws ProjectException {
		when(repository.findAll()).thenReturn(list);
		ArrayList<Project> iter = projectCtrl.getAllProjects();
		List<Project> list2 = new ArrayList<Project>();
		iter.forEach(list2::add);
		Assert.assertEquals(2, list2.size());
		Assert.assertEquals(2, list.size());
		Assert.assertNotNull(iter);
	}

	@Test
	public void deleteProject() throws ProjectException, ParseException {

		Project pro = new Project(101L, "Project_101", "Project_101_30days", sqlStartDate, sqlEndDate, "BU_123");
		// define return value for method save()
		when(repository.findById(any(Long.class))).thenReturn(java.util.Optional.of(pro));
		Assert.assertEquals(pro, projectCtrl.deleteProjectById(101L));
		doNothing().when(repository).deleteById(any(Long.class));
		Assert.assertEquals(pro, projectCtrl.deleteProjectById(101L));

	}

}
