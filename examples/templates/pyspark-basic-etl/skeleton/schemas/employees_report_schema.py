from pyspark.sql.types import StructType, StructField, StringType, IntegerType

employees_report_schema = StructType([
    StructField("id", IntegerType(), nullable=False),
    StructField("name", StringType(), nullable=False),
    StructField("steps_to_desk", IntegerType(), nullable=False)
])
