package uk.co.jemos.podam.test.unit.features.validatorFramework;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.ValidatedPojo;
import uk.co.jemos.podam.test.dto.ValidatedPojoMultipleConstraints;
import uk.co.jemos.podam.test.dto.ValidationPojoForStringWithSizeAndNoMax;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import javax.validation.Validator;

/**
 * Tests Java bean validation API
 *
 * @author daivanov
 */
@RunWith(SerenityRunner.class)
public class ValidatedPojoTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should be able to fulfill most of the javax Validation framework")
	public void podamShouldFulfillMostOfTheJavaxValidationFramework() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithEmailStrategy();

		ValidatedPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ValidatedPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getBoolFalse());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getBoolTrue());
		podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getFilledString());
		podamValidationSteps.theObjectShouldBeNull(pojo.getEmptyString());
		podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getNotEmptyString());
		podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getNotBlankString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDecimalDouble());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDecimalFloat());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDecimalString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getLongNumber());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getIntNumber());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getBigIntNumber());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getShortNumber());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getByteNumber());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getIntString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getFractionDecimal());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getFractionString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getPastDate());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getFutureCalendar());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getSizedString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getMaxCollection());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getMinCollection());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDefaultCollection());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDefaultMap());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getEmail());
		podamValidationSteps.theObjectShouldBeNull(pojo.getIdentifier());

		Validator validator = podamFactorySteps.givenAJavaxValidator();
		validatorSteps.thePojoShouldNotViolateAnyValidations(validator, pojo);

	}

	@Test
	@Title("When the @Size annotation doesn't have a max length specified, Podam should assign a sensible value")
	public void whenMaxLengthIsNotSpecifiedInSizeAnnotationPodamShouldAssignASensibleDefault()
			throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ValidationPojoForStringWithSizeAndNoMax pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForClass(
						ValidationPojoForStringWithSizeAndNoMax.class,
						podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);

		Validator validator = podamFactorySteps.givenAJavaxValidator();
		validatorSteps.thePojoShouldNotViolateAnyValidations(validator, pojo);

	}

	@Test
	@Title("When the @Digits and @DecimalMin and @DecimalMax applied to the same field, Podam should use minimum requirements")
	public void whenDigitsAndDecimalMinOrDecimalMaxAreAttachedToTheSameFieldPodamShouldUseMinimumRequirement()
			throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ValidatedPojoMultipleConstraints pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForClass(
						ValidatedPojoMultipleConstraints.class,
						podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);

		Validator validator = podamFactorySteps.givenAJavaxValidator();
		validatorSteps.thePojoShouldNotViolateAnyValidations(validator, pojo);

	}

}
