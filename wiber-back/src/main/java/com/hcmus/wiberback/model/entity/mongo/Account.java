package com.hcmus.wiberback.model.entity.mongo;

import com.hcmus.wiberback.model.enums.Role;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString(exclude = {"password"})
@NoArgsConstructor
public class Account extends BaseEntity {

  @NotEmpty
  @NotBlank
  @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
  private String phone;

  @NotNull
  private String password;

  @NotNull
  private Role role;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Account)) {
      return false;
    }
    Account account = (Account) o;
    return phone.equals(account.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phone, password);
  }
}
