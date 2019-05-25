package admin.core.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {

    private Long id;
    private String tsCode;
    private String username;
}
