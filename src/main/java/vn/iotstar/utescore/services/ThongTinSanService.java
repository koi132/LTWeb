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

	public Thongtinsan addThongTinSan(String fieldName, String type, double price, String detail, String address,
			String facilities) {
		Thongtinsan thongtinsan = new Thongtinsan();
		thongtinsan.setFieldName(fieldName);
		thongtinsan.setType(type);
		thongtinsan.setPrice(price);
		thongtinsan.setDetail(detail);
		thongtinsan.setAddress(address);
		thongtinsan.setFacilities(facilities);
		return thongTinSanRepository.save(thongtinsan);
	}

	public List<Thongtinsan> getAllFields() {
		return thongTinSanRepository.findAll();
	}

	public Optional<Thongtinsan> getFieldById(Integer id) {
		return thongTinSanRepository.findById(id);
	}

	public Thongtinsan getThongTinSanById(int id) {
		return thongTinSanRepository.findById(id).orElse(null);
	}

	public Thongtinsan updateThongTinSan(Thongtinsan thongtinsan) {
		return thongTinSanRepository.save(thongtinsan);
	}
}
