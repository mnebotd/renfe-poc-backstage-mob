from pyspark.sql.types import StructType, StructField, StringType, IntegerType

employees_schema = StructType([
    StructField("id", IntegerType(), nullable=False),
    StructField("first_name", StringType(), nullable=False),
    StructField("second_name", StringType(), nullable=False),
    StructField("floor", IntegerType(), nullable=False)
])
