package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("программист");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        store.add(new Role("1", "тестировщик"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("программист");
    }

    @Test
    void whenReplaceThenNameIsTester() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        store.replace("1", new Role("1", "тестировщик"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("тестировщик");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        store.replace("10", new Role("10", "тестировщик"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("программист");
    }

    @Test
    void whenDeleteRoleThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("программист");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        boolean result = store.replace("1", new Role("1", "тестировщик"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "программист"));
        boolean result = store.replace("10", new Role("10", "тестировщик"));
        assertThat(result).isFalse();
    }
}