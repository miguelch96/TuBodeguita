package pe.com.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import pe.com.core.business.CategoriaBusiness;
import pe.com.core.entity.Categoria;
import pe.com.test.bean.CategoriaBean;

public class CategoriaTest {
	
	private final CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
	private static Categoria categoria;
	
	@BeforeClass
	public void inicioClase() {
		System.out.println("**********************Inicio Clase CategoriaTest**********************");
	}

	@AfterClass
	public void finClase() {
		System.out.println("**********************Fin Clase CategoriaTest**********************");
	}
	
	@BeforeTest
	public void inicioMetodo() {
		System.out.println("**********************Inicio Metodo CategoriaTest**********************");
	}

	@AfterTest
	public void finMetodo() {
		System.out.println("Id Categoria: " + categoria.getIdCategoria());
		System.out.println("Nombre: " + categoria.getNombre());
		System.out.println("**********************Fin Metodo CategoriaTest**********************\n\n");
	}
	
	@DataProvider(name = "datosCategoria")
	public static Object[][] datosCategoria() {
		return new Object[][] {
			{new CategoriaBean("Chocolates")},
			{new CategoriaBean("Lacteos")},
			{new CategoriaBean("Menestras")}
		};
	}
	

	@Test
	@Parameters({"nombreInsertar", "nombreActualizar"})
	public void insertar(String variableIns, String variableAct) {
		try{
			System.out.println("Metodo Insertar");
			categoria = new Categoria();
			categoria.setNombre(variableIns);
			categoriaBusiness.insertar(categoria);
			Assert.assertTrue(categoria.getIdCategoria()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dataProvider = "datosCategoria")
	public void insertarDataProvider(CategoriaBean categoriaBean) {
		try{
			System.out.println("Metodo Insertar");
			categoria = new Categoria();
			categoria.setNombre(categoriaBean.getNombre());
			categoriaBusiness.insertar(categoria);
			Assert.assertTrue(categoria.getIdCategoria()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods={"insertar"})
	public void actualizar() {
		try{
			System.out.println("Metodo Actualizar");
			categoria.setNombre("Prueba Actualizada");
			categoriaBusiness.actualizar(categoria);
			Assert.assertTrue(categoria.getIdCategoria()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods={"actualizar"})
	public void obtener(){
		try{
			System.out.println("Metodo Obtener");
			Categoria categoriaBuscada = categoriaBusiness.obtener(categoria.getIdCategoria());
			Assert.assertEquals(categoria, categoriaBuscada);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods={"obtener"})
	public void eliminar(){
		try{
			System.out.println("Metodo Eliminar");
			categoriaBusiness.eliminar(categoria);
			Assert.assertTrue(true);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	

}
