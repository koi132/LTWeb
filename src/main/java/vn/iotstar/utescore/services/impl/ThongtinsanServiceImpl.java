package vn.iotstar.utescore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.repository.ThongTinSanRepository;
import vn.iotstar.utescore.services.ThongtinsanService;

@Service
public class ThongtinsanServiceImpl implements ThongtinsanService {

    @Autowired
    private ThongTinSanRepository thongtinsanRepository;

    @Override
    public void addField(Thongtinsan thongtinsan) {
        thongtinsanRepository.save(thongtinsan);
    }
}
