package ua.hackhud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course code is required")
    @Size(min = 3, max = 10, message = "Course code must be 3-10 characters")
    @Column(unique = true)
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Credit hours are required")
    @Min(value = 1, message = "Minimum credit hours is 1")
    @Max(value = 5, message = "Maximum credit hours is 5")
    private Integer creditHours;
}