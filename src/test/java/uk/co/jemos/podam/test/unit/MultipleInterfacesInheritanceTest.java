package uk.co.jemos.podam.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.test.dto.MultipleInterfacesHolderPojo;
import uk.co.jemos.podam.test.dto.MultipleInterfacesListPojo;
import uk.co.jemos.podam.test.dto.MultipleInterfacesMapPojo;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 * @author daivanov
 */
public class MultipleInterfacesInheritanceTest {

	private static class CustomDataProviderStrategy
			extends AbstractRandomDataProviderStrategy {
		private List<Class<?>> accessed = new ArrayList<Class<?>>();

		@Override
		public <T> Class<? extends T> getSpecificClass(
				Class<T> nonInstantiatableClass) {
			accessed.add(nonInstantiatableClass);
			return nonInstantiatableClass;
		}

		public List<Class<?>> getAccessed() {
			return accessed;
		}

	};

	private static final CustomDataProviderStrategy strategy
			= new CustomDataProviderStrategy();

	private static final PodamFactory factory = new PodamFactoryImpl(strategy);

	@Test
	public void testListPojoWithMultiInterfaces() {

		MultipleInterfacesListPojo pojo = factory.manufacturePojo(
				MultipleInterfacesListPojo.class, String.class);
		Assert.assertNull("Interface POJO cannot be constructed", pojo);
		List<Class<?>> accessed = strategy.getAccessed();
		Assert.assertEquals(1, accessed.size());
		accessed.clear();
	}

	@Test
	public void testMapPojoWithMultiInterfaces() {

		MultipleInterfacesMapPojo pojo = factory.manufacturePojo(
				MultipleInterfacesMapPojo.class, String.class, Long.class);
		Assert.assertNull("Interface POJO cannot be constructed", pojo);
		List<Class<?>> accessed = strategy.getAccessed();
		Assert.assertEquals(1, accessed.size());
		accessed.clear();
	}

	@Test
	public void testHolderOfPojoWithMultiInterfaces() {

		MultipleInterfacesHolderPojo pojo = factory.manufacturePojo(
				MultipleInterfacesHolderPojo.class, String.class, String.class, Long.class);
		Assert.assertNotNull("POJO cannot be null", pojo);
		Assert.assertNull("POJO's interface value cannot be constructed",
				pojo.getList());
		Assert.assertNull("POJO's interface value cannot be constructed",
				pojo.getMap());
		List<Class<?>> accessed = strategy.getAccessed();
		Assert.assertEquals(2, accessed.size());
		Assert.assertEquals(MultipleInterfacesListPojo.class, accessed.get(0));
		Assert.assertEquals(MultipleInterfacesMapPojo.class, accessed.get(1));
		accessed.clear();
	}
}
