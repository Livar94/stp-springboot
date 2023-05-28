import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private Map<String, String> players = new HashMap<>();

    @PostMapping("/register")
    public ResponseEntity<String> registerPlayer(@RequestParam String playerId) {
        if (players.containsKey(playerId)) {
            return ResponseEntity.badRequest().body("Player ID already exists");
        }
        players.put(playerId, "");
        return ResponseEntity.ok("Player registered successfully");
    }

    @PostMapping("/play")
    public ResponseEntity<GameResult> play(
            @RequestParam String playerId,
            @RequestParam String move) {
        if (!players.containsKey(playerId)) {
            return ResponseEntity.badRequest().body(null);
        }
        players.put(playerId, move);
        if (players.containsValue("")) {
            return ResponseEntity.ok(null);
        } else {
            GameResult result = determineWinner();
            players.clear();
            return ResponseEntity.ok(result);
        }
    }

    private GameResult determineWinner() {

    }
}
