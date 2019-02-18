package challenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4542150506716930152L;

	public ResourceNotFoundException() {
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
