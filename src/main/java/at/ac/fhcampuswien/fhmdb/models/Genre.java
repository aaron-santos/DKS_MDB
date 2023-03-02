package at.ac.fhcampuswien.fhmdb.models;

public enum Genre {
    ACTION,
    ADVENTURE,
    ANIMATION,
    BIOGRAPHY,
    COMEDY,
    CRIME,
    DRAMA,
    DOCUMENTARY,
    FAMILY,
    FANTASY,
    HISTORY,
    HORROR,
    MUSICAL,
    MYSTERY,
    ROMANCE,
    SCIENCE_FICTION,
    SPORT,
    THRILLER,
    WAR,
    WESTERN;

    @Override
    public String toString() {
        String genreName = name().toLowerCase().replaceAll("_", " ");
        StringBuilder sb = new StringBuilder();
        boolean nextIsUpper = true;
        for (char c : genreName.toCharArray()) {
            if (c == ' ') {
                nextIsUpper = true;
                sb.append(c);
            } else if (nextIsUpper) {
                sb.append(Character.toUpperCase(c));
                nextIsUpper = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static String normalize(String genreString) {
        return genreString.replace(" ", "_").toUpperCase();
    }

}