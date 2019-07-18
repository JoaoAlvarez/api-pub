package br.com.api.services.validation;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.api.domain.enums.TipoCliente;
import br.com.api.dto.ClienteNewDTO;
import br.com.api.resources.excetion.FieldMessage;
import br.com.api.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		/*
		 * if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) &&
		 * !BR.isValidCPF(objDto.getCpfCnpj())) { list.add(new
		 * FieldMessage("cpfCnpj","Cpf inválido")); }
		 * 
		 * if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) &&
		 * !BR.isValidCNPJ(objDto.getCpfCnpj())) { list.add(new
		 * FieldMessage("cpfCnpj","CNPJ inválido")); }
		 */
			
		if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
			
			
