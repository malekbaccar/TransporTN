package com.example.transporttn.servicesIMPL;

import com.example.transporttn.entites.Role;
import com.example.transporttn.repositories.RoleRepository;
import com.example.transporttn.services.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RoleServiceIMPL implements IRoleService {


	private final RoleRepository rolesRepository;

	@Override
	public void createNewRole( Role role) {

		rolesRepository.save(role);
		
	}


}
