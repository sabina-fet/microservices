import json
from kafka import KafkaConsumer
from kafka.errors import KafkaError
from neo4j import GraphDatabase

if __name__ == '__main__':
    consumer = KafkaConsumer(
        'transactions',
        bootstrap_servers=['broker:9092'],
        group_id="k2n4j_consumers"
    )
    n4j = GraphDatabase.driver("bolt://neo4j:7687")

    for msg in consumer:
        try:
            t = json.loads(msg.value)
            print(f'Message received: ${t}')
            source = t.get('source')
            destination = t.get('destination')
            amount = t.get('amount')
            datetime = t.get('datetime')

            # Merge the accounts first
            n4j.execute_query(
            """
            MERGE (a:Account { id: $from_id })
            MERGE (b:Account { id: $to_id })
            """,
            from_id=source,
            to_id=destination
            )
        
            # Now create or update the relationship
            n4j.execute_query(
            """
            MATCH (a:Account { id: $from_id })
            MATCH (b:Account { id: $to_id })
            MERGE (a)-[r:Send { time: $tr_time }]->(b)
            SET r.amount = $amount
            """,
            from_id=source,
            to_id=destination,
            tr_time=datetime,
            amount=amount
            )
        except KafkaError as e:
            print(f'Kafka error occured: ${e}')
        except Exception as e:
            print(f'Unable to process kafka message: ${e}')