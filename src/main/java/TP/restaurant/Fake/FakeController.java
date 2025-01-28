package TP.restaurant.Fake;

import org.springframework.web.bind.annotation.*;

@RestController
public class FakeController {

    @GetMapping("/fake")
    public String fake() {
        return "Fake";
    }
    @GetMapping("/fake/params")
    public String fakeParams(@RequestParam String name) {
        if (name.isBlank()) {
            return "No name";
        }
        return name;
    }
    @GetMapping("/fake/{condition}")
    public String fakeCondition(@PathVariable boolean condition) {
        return condition ? "YES" : "NO";
    }
    @PostMapping("/fake")
    public FakeObject fakePost(@RequestBody FakeObject body) {
        return body;
    }
}
