```python
book = Book.objects.get(title="1984")
book.title = "Nineteen Eighty-Four"
book.save()
print(f"Updated title: {book.title}")
Output:
Updated title: Nineteen Eighty-Four
