package vn.iotstar.utescore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.repository.ThongTinSanRepository;


@Service
public class ThongTinSanService {
	@Autowired
    private ThongTinSanRepository thongTinSanRepository;
	
    public Thongtinsan addThongTinSan(String fieldName, String type) {
        Thongtinsan thongtinsan = new Thongtinsan(fieldName, type, 0, type);
        return thongTinSanRepository.save(thongtinsan);
    }     
    
    public List<Thongtinsan> getAllFields() {
		return thongTinSanRepository.findAll();
	}

	public Optional<Thongtinsan> getFieldById(Integer id) {
		return thongTinSanRepository.findById(id);
	}
}
