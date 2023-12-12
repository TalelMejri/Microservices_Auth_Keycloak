package org.sid.repository;

import java.util.Collection;
import java.util.Date;
import org.sid.entity.Bill;
import org.sid.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;


@RepositoryRestResource
public interface BillRepo extends JpaRepository<Bill,Long> {

}


