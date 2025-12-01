from pyspark.sql import Row

from jobs.transform.transform_data import transform_data

def create_test_data(spark, config):
    """
    Create local example data for unit testing.
    """
    local_records = [
        Row(id=1, first_name='Dan', second_name='Germain', floor=1),
        Row(id=2, first_name='Dan', second_name='Sommerville', floor=1),
        Row(id=3, first_name='Alex', second_name='Ioannides', floor=2),
        Row(id=4, first_name='Ken', second_name='Lai', floor=2),
        Row(id=5, first_name='Stu', second_name='White', floor=3),
        Row(id=6, first_name='Mark', second_name='Sweeting', floor=3),
        Row(id=7, first_name='Phil', second_name='Bird', floor=4),
        Row(id=8, first_name='Kim', second_name='Suter', floor=4),
    ]

    df = spark.createDataFrame(local_records)

    # Input parquet
    (
        df
        .coalesce(1)
        .write
        .parquet('tests/test_data/employees', mode='overwrite')
    )

    # Transformed parquet
    df_tf = transform_data(df, config['steps_per_floor'])

    (
        df_tf
        .coalesce(1)
        .write
        .parquet('tests/test_data/employees_report', mode='overwrite')
    )
