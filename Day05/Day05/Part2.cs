namespace Day05;

public class Part2
{
    private static List<int> FixPublicationOrder(List<int> publication, List<(int, int)> rules)
    {
        var isFaulty = true;
        var fixedPublication = new List<int>(publication);

        while (isFaulty)
        {
            foreach (var rule in rules)
                if (fixedPublication.Contains(rule.Item1) && fixedPublication.Contains(rule.Item2))
                    if (fixedPublication.IndexOf(rule.Item1) > fixedPublication.IndexOf(rule.Item2))
                    {
                        fixedPublication[fixedPublication.IndexOf(rule.Item1)] = rule.Item2;
                        fixedPublication[fixedPublication.IndexOf(rule.Item2)] = rule.Item1;
                    }

            if (Part1.IsValidPublication(fixedPublication, rules))
                isFaulty = false;
        }

        return fixedPublication;
    }

    public static int SolvePart2(string input)
    {
        var (rules, publications) = Part1.ParseInput(input);
        var sortedRules = rules
            .OrderByDescending(rule => rule)
            .ToList();

        return publications
            .Where(publication => !Part1.IsValidPublication(publication, rules))
            .Select(publication => FixPublicationOrder(publication, sortedRules))
            .Select(list => list[list.Count / 2])
            .Sum();
    }
}