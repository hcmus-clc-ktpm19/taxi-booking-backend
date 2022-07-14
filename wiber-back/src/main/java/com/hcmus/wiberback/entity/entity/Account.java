package com.hcmus.wiberback.entity.entity;

import com.hcmus.wiberback.entity.enums.Role;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString(exclude = {"password"})
@NoArgsConstructor
public class Account extends BaseEntity implements Serializable {

  @NotEmpty
  @NotBlank
  @Digits(integer = 10, fraction = 0)
  private String phone;

  @NotEmpty
  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
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
