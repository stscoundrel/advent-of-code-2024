package day13

import "core:fmt"
import "core:strings"
import "core:strconv"
import "core:os"

Coordinate :: struct {
    x: int,
    y: int
}

Game :: struct {
    a: Coordinate,
    b: Coordinate,
    prize: Coordinate,
}

extract_prize_coordinates :: proc(input: string) -> (int, int) {
   parts := strings.split(input, "=")
   x_part := strings.split(parts[1], ",")[0]
   y_part := parts[2]

   return strconv.atoi(x_part), strconv.atoi(y_part)
}

extract_button_coordinates :: proc(input: string) -> (int, int) {
    parts := strings.split(input, "+")
    x_part := strings.split(parts[1], ",")[0]
    y_part := parts[2]
 
    return strconv.atoi(x_part), strconv.atoi(y_part)
 }

parse_input :: proc(input: string) -> [dynamic]Game {
    games: [dynamic]Game
    game_parts := strings.split(input, "\n\n")
    
    for game, index in game_parts {
        parts := strings.split(game, "\n")

        a_x, a_y := extract_button_coordinates(parts[0])
        b_x, b_y := extract_button_coordinates(parts[1])
        prize_x, prize_y := extract_prize_coordinates(parts[2])
        
        button_a := Coordinate{a_x, a_y}
        button_b := Coordinate{b_x, b_y}
        prize := Coordinate{prize_x, prize_y}

        append(&games, Game{ button_a, button_b, prize })
    }
    
    return games
}

get_cost :: proc(game: Game) -> [dynamic]int {
    winning_games: [dynamic]int

    for a in 0..=100 {
        for b in 0..=100 {
            if (game.a.x * a + game.b.x * b == game.prize.x && game.a.y * a + game.b.y * b == game.prize.y ) {
                append(&winning_games, 3*a + b)
            }
        }
    }

    return winning_games
}


main :: proc() {     
    testInput :: `Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279`

    raw_input, ok := os.read_entire_file("input.txt");
    input := string(raw_input)

    games := parse_input(input)
    total_cost := 0

    for game, index in games {
        costs := get_cost(game)

        for cost in costs {
            total_cost += cost
        }
    }

    fmt.println(total_cost)
}