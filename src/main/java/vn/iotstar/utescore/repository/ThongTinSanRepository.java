package vn.iotstar.utescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.utescore.entity.Thongtinsan;

@Repository
public interface ThongTinSanRepository extends JpaRepository<Thongtinsan, Integer> {
	
}
