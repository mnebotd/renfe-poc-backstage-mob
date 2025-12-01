from pyspark.sql import SparkSession
from schemas.schema_validation import validate_schema
from schemas.employees_schema import employees_schema

def extract_data(spark: SparkSession):
    """
    Extract step: Load data from Parquet.

    :param spark: Spark session object.
    :return: Spark DataFrame.
    """
    df = spark.read.parquet("tests/test_data/employees")

    # Validate input schema
    validate_schema(df, employees_schema)

    return df
