package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import url.models.Url;

@Repository
public interface UrlRepository extends JpaRepository <Url, Integer> {

}
