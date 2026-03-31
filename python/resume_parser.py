import pymupdf


def extract_text(pdf_path: str) -> str:
    """Extract all text from a PDF file using PyMuPDF."""
    doc = pymupdf.open(pdf_path)
    text_parts = []
    for page in doc:
        text_parts.append(page.get_text())
    doc.close()
    return "\n".join(text_parts).strip()