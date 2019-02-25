package rest.DishOfTheDay.domain.base;

/* https://github.com/Cepr0/sb-oauth-security-demo/blob/master/src/main/java/com/github/cepr0/demo/security/model/IntIdEntity.java */

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class JsonEntity extends rest.DishOfTheDay.domain.base.BaseEntity {

}
