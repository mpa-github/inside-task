package com.mpa.insidetask.configurations;

import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.services.RoleService;
import com.mpa.insidetask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseDataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataBaseDataLoader(UserService userService,
                              RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findUser("admin") == null) {
            createRoles();
            createAdministrator();
        }
    }

    private void createAdministrator() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        admin.getRoles().add(roleService.findRole("ROLE_ADMIN"));
        userService.register(admin);
    }

    private void createRoles() {
        roleService.createRole("ROLE_ADMIN");
        roleService.createRole("ROLE_USER");
    }
}
