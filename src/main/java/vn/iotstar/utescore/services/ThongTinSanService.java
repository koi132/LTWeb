package vn.iotstar.utescore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.repository.ThongTinSanRepository;


@Service
public class ThongTinSanService {

	@Autowired
    private ThongTinSanRepository thongTinSanRepository;

    public Thongtinsan addThongTinSan(String fieldName, String type) {
        Thongtinsan thongtinsan = new Thongtinsan(fieldName, type);
        return thongTinSanRepository.save(thongtinsan); // Lưu sân vào cơ sở dữ liệu
    }
}
