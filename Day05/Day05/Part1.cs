namespace Day05;

public class Part1
{
    public static (List<(int, int)> Rules, List<List<int>> Publications) ParseInput(string input)
    {
        var parts = input.Split(new[] { "\n\n" }, StringSplitOptions.RemoveEmptyEntries);
        
        var rules = parts[0]
            .Split(new[] { '\n' }, StringSplitOptions.RemoveEmptyEntries)
            .Select(rule =>
            {
                var numbers = rule.Split('|').Select(int.Parse).ToArray();
                return (numbers[0], numbers[1]);
            })
            .ToList();


        var publications = parts[1]
            .Split(new[] { '\n' }, StringSplitOptions.RemoveEmptyEntries)
            .Select(publication =>
                publication.Split(',')
                    .Select(int.Parse)
                    .ToList()
            )
            .ToList();

        return (rules, publications);
    }

    public static bool IsValidPublication(List<int> publication, List<(int, int)> rules)
    {
        foreach (var rule in rules)
            if (publication.Contains(rule.Item1) && publication.Contains(rule.Item2))
                if (publication.IndexOf(rule.Item1) > publication.IndexOf(rule.Item2))
                    return false;

        return true;
    }

    public static int SolvePart1(string input)
    {
        var (rules, publications) = ParseInput(input);

        return publications
            .Where(publication => IsValidPublication(publication, rules))
            .Select(list => list[list.Count / 2])
            .Sum();
    }
}