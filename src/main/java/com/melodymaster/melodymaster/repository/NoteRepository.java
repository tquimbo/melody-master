import org.springframework.data.jpa.repository.JpaRepository;
import com.melodymaster.melodymaster.entity.Notes;

public interface NoteRepository extends JpaRepository<Notes, Long> {
}