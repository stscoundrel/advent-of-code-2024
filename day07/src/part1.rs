#[derive(Debug, Clone, Copy)]
enum Operation {
    Add,
    Multiply,
}

fn parse_input(input: &str) -> Vec<(u64, Vec<u64>)> {
    input
        .lines()
        .map(|line| {
            let mut parts = line.split(':');
            let sum = parts
                .next()
                .unwrap() 
                .trim()
                .parse::<u64>()
                .unwrap();
            let numbers = parts
                .next()
                .unwrap() 
                .split_whitespace() 
                .map(|n| n.parse::<u64>().unwrap()) 
                .collect::<Vec<u64>>();
            (sum, numbers)
        })
        .collect()
}

fn generate_combinations(n: usize) -> Vec<Vec<Operation>> {
    let num_combinations = 1 << n;
    let mut combinations = Vec::new();

    for i in 0..num_combinations {
        let mut combination = Vec::new();
        for j in 0..n {
            if (i & (1 << j)) != 0 {
                combination.push(Operation::Add);
            } else {
                combination.push(Operation::Multiply);
            }
        }
        combinations.push(combination);
    }

    combinations
}

fn count_possibles(sum: u64, numbers: &[u64]) -> u64 {
    let num_operations = numbers.len().saturating_sub(1);
    let combinations = generate_combinations(num_operations);
    let mut valid_combinations = 0;

    for combination in combinations {
        let mut result = numbers[0];
        
        for (i, &num) in numbers.iter().skip(1).enumerate() {
            match combination[i] {
                Operation::Add => result += num,
                Operation::Multiply => result *= num,
            }
        }

        if result == sum {
            valid_combinations += 1;
        }
    }

    valid_combinations
}

pub fn solve_part_1(input: &str) -> u64 {
    let mut result = 0;
    let parsed = parse_input(input);    

    for (sum, numbers) in parsed {
        let possibles = count_possibles(sum, &numbers);
        if possibles > 0 {
            result += sum;
        }
    }

   result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn solves_part_1() {
        const TEST_INPUT: &str = r#"190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20"#;

        let result = solve_part_1(TEST_INPUT);
        assert_eq!(result, 3749);
    }
}