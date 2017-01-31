/**
* <p>Title: PersonRepository.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: UOB</p>
* @author RuJia
* @date 2017年1月31日
* @version 1.0
*/
package com.bham.sso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bham.sso.entity.Person;
/**
* <p>Title: PersonRepository.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: UOB</p>
* @author RuJia
* @date 2017年1月31日
* @version 1.0
*/
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
