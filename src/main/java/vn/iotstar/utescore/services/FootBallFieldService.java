package vn.iotstar.utescore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.utescore.entity.FootballField;
import vn.iotstar.utescore.repository.FootballFieldRepository;

@Service
public class FootBallFieldService {
	private FootballFieldRepository footballFieldRepository;

	@Autowired // Ensure this constructor is used
	public FootBallFieldService(FootballFieldRepository footballFieldRepository) {
		this.footballFieldRepository = footballFieldRepository;
	}

	public List<FootballField> getAllFields() {
		return footballFieldRepository.findAll();
	}

	public Optional<FootballField> getFieldById(Long id) {
		return footballFieldRepository.findById(id);
	}
}
