package py.com.leketo.paymentServices.model.auth.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanUserResponse {
    User user;
}
