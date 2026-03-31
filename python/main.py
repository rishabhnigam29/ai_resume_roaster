import sys
from pathlib import Path

from dotenv import load_dotenv

from resume_parser import extract_text
from roaster import roast_resume


def print_results(roast):
    print("\n" + "=" * 60)
    print("  RESUME ROAST RESULTS")
    print("=" * 60)

    print(f"\n  Overall Score: {roast.overall_score}/100\n")

    print("  Category Scores:")
    for category, score in roast.category_scores.items():
        label = category.replace("_", " ").title()
        bar = "#" * (score // 5) + "-" * (20 - score // 5)
        print(f"    {label:<25} [{bar}] {score}/100")

    print(f"\n{'=' * 60}")
    print("  THE ROAST")
    print(f"{'=' * 60}")
    print(f"\n{roast.roast}\n")

    print(f"{'=' * 60}")
    print("  WEAKEST SECTIONS")
    print(f"{'=' * 60}")
    for i, section in enumerate(roast.weakest_sections, 1):
        print(f"\n  {i}. {section.section_name}")
        print(f"     {section.callout}")

    print(f"\n{'=' * 60}")
    print("  SUGGESTED REWRITES")
    print(f"{'=' * 60}")
    for i, rewrite in enumerate(roast.rewrites, 1):
        print(f"\n  {i}. {rewrite}")

    print(f"\n{'=' * 60}")
    print("  VERDICT")
    print(f"{'=' * 60}")
    print(f"\n  {roast.verdict}\n")


def main():
    if len(sys.argv) != 2:
        print("Usage: python main.py <path-to-resume.pdf>")
        sys.exit(1)

    pdf_path = sys.argv[1]
    if not Path(pdf_path).exists():
        print(f"Error: File not found: {pdf_path}")
        sys.exit(1)

    load_dotenv()

    print(f"Parsing resume: {pdf_path}")
    text = extract_text(pdf_path)
    if not text:
        print("Error: Could not extract any text from the PDF.")
        sys.exit(1)

    print(f"Extracted {len(text)} characters. Sending to LLM...")
    roast = roast_resume(text)
    print_results(roast)


if __name__ == "__main__":
    main()
