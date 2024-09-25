FROM python:3.10
WORKDIR /app

COPY ./k2n4j.py .
COPY ./requirements.txt .

RUN pip install --no-cache-dir -r /app/requirements.txt

CMD ["python", "k2n4j.py"]