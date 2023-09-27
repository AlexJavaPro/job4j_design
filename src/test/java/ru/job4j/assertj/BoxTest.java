package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotBlank()
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(String.class)
                .containsIgnoringCase("Sphere")
                .doesNotContain("java")
                .startsWith("Sph")
                .endsWith("ere")
                .startsWithIgnoringCase("spHe")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(10, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isNotBlank()
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(String.class)
                .containsIgnoringCase("Unknown object")
                .doesNotContain("java")
                .startsWith("Unknown")
                .endsWith("object")
                .startsWithIgnoringCase("UnknOWN")
                .isEqualTo("Unknown object");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name)
                .isNotBlank()
                .isNotNull()
                .isNotEmpty()
                .isInstanceOf(String.class)
                .containsIgnoringCase("Tetrahedron")
                .doesNotContain("java")
                .startsWith("Tetra")
                .endsWith("hedron")
                .startsWithIgnoringCase("teTra")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 7);
        String name = box.whatsThis();
        assertThat(name)
                .isNotBlank()
                .isNotNull()
                .isNotEmpty()
                .containsIgnoringCase("Cube")
                .doesNotContain("java")
                .isInstanceOf(String.class)
                .startsWith("Cu")
                .endsWith("be")
                .startsWithIgnoringCase("cUbe")
                .isEqualTo("Cube");
    }

    @Test
    void whenVertex8Edge7Then294() {
        Box box = new Box(8, 7);
        double area = box.getArea();
        assertThat(area).isEqualTo(294d, withPrecision(0.1d))
                .isCloseTo(294d, withPrecision(0.1d))
                .isCloseTo(294d, Percentage.withPercentage(1.0d))
                .isGreaterThan(293d)
                .isLessThan(295d)
                .isNotZero()
                .isPositive();
    }
    @Test
    void isExit() {
        Box box = new Box(8, 0);
        boolean exit = box.isExist();
        assertThat(exit)
                .isFalse()
                .isEqualTo(false);
    }
}