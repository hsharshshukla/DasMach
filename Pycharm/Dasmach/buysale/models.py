from django.db import models


# Create your models here.
class newsletter(models.Model):
    newsletter_email = models.EmailField(max_length=200)

class contact (models.Model):
    contact_name = models.CharField(max_length=50)
    contact_email = models.EmailField(max_length=80)
    contact_mobile = models.CharField(max_length=10)
    contact_message = models.TextField(max_length=200)

class car(models.Model):
    # Drop Down settings
    # mfg_year
    MFG_YR = [
        ('', 'Year'),
        ('1', '2018'),
        ('2', '2017'),
        ('3', '2016'),
        ('4', '2015'),
        ('5', '2014'),
    ]
    # Month
    Month = [
        ('', 'Month'),
        ('	1	', '	Jan	'),
        ('	2	', '	Feb	'),
        ('	3	', '	Mar	'),
        ('	4	', '	Apr	'),
        ('	5	', '	May	'),
        ('	6	', '	Jun	'),
        ('	7	', '	Jul	'),
        ('	8	', '	Aug	'),
        ('	9	', '	Sep	'),
        ('	10	', '	Oct	'),
        ('	11	', '	Nov	'),
        ('	12	', '	Dec	'),
    ]

    Make = [
        ('', 'Brand'),
        ('1', 'Mercedes'),
        ('2', 'Audi'),
        ('3', 'BMW'),
        ('4', 'MiNi'),
        ('5', 'Volvo'),
        ('6', 'Roll Royce'),
        ('7', 'Lamborgini'),
        ('8', 'Ferrari'),
        ('9', 'Bugatti'),
        ('10', 'Aston Martin'),
        ('11', 'Land Rover'),
        ('12', 'Jaguar'),
        ('13', 'Porsche'),
        ('14', 'Maserati'),
        ('15', 'Bentley'),
    ]

    # Owner
    Owner = [
        ('', 'Owner'),
        ('1', 'First'),
        ('2', 'Second'),
        ('3', 'Third'),
        ('4', 'Company Owned'),
    ]

    # Make/Brand


    # km
    km = [
        ('', 'Km'),
        ('1', 'Below 10000'),
        ('2', '10000-25000'),
        ('3', '25000+')
    ]
    # Variants
    Variant = [
        ('', 'Variant'),
        ('1', 'Top-Diesel'),
        ('2', 'Top-Petrol'),
        ('3', 'Mi-Diesel'),
        ('4', 'Mi-Petrol'),
        ('5', 'Low-Diesel'),
        ('6', 'Low-Petrol'),
        ('7', 'Hybrid'),
    ]

    # Color
    Color = [
        ('', 'Color'),
        ('1', 'BEIG'),
        ('2', 'BLACK'),
        ('3', 'BLUE'),
        ('4', 'BRONZE'),
        ('5', 'BROWN'),
        ('6', 'GOLD'),
        ('7', 'GREEN'),
        ('8', 'GREY'),
        ('9', 'MAROON'),
        ('10', 'ORANGE'),
        ('11', 'PURPLE'),
        ('12', 'RED'),
        ('13', 'SILVER'),
        ('14', 'WHITE'),
        ('15', 'YELLOW'),
    ]

    # Fuel
    Fuel = [
        ('', 'Fuel'),
        ('1', 'Petrol'),
        ('2', 'Diesel'),
        ('3', 'Hybrid'),
        ('4', 'Electric'),
        ('5', 'Hydrogen'),
    ]
    # Use Type
    UseType = [
        ('', 'Use'),
        ('1', 'Personal'),
        ('2', 'Commercial'),
    ]

    # Insurance Type
    InsuranceType = [
        ('1', 'Comprehensive'),
        ('2', 'Third Party'),
        ('3', 'Expired'),
    ]
    # Accident,InsuranceClaimed,ServicefromAuthorisedDealer
    YesNo = [
        ('1', 'No'),
        ('2', 'Yes'),
    ]

    # Owners Profession
    # Owners Income

    # Alternate Fuel
    # Km/miles

    owner_name = models.CharField(max_length=50)
    owner_email = models.EmailField(max_length=200)
    owner_mobile = models.CharField(max_length=10)
    buy_year = models.CharField(choices= MFG_YR, max_length=200)
    veh_make_month = models.CharField(choices=Month, max_length=200)
    veh_owner = models.CharField(choices=Owner, max_length=200)
    veh_make = models.CharField(max_length=200, choices=Make)
    veh_km = models.CharField(choices=km, max_length=200)
    veh_variant = models.CharField(choices=Variant, max_length=200)
    veh_Color = models.CharField(choices=Color, max_length=200)
    veh_fuel = models.CharField(choices=Fuel, max_length=200)
    veh_usetype = models.CharField(choices=UseType, max_length=200)
    veh_Insurancetype = models.CharField(choices=InsuranceType, max_length=200, default='Comprehensive')
    veh_accident = models.CharField(max_length=200, choices=YesNo, default='No')
    veh_Insuranceclaimed = models.CharField(choices=YesNo, max_length=200, default='No')
    veh_Insuranceclaimamount = models.IntegerField(default=0)
    veh_servicehistory = models.CharField(max_length=200, choices=YesNo, default='No')
    veh_existingLoan = models.CharField(max_length=200, choices=YesNo, default='No')
    veh_image1 = models.FileField(null=True, blank=True, upload_to='photo')


    def __unicode__(self):
        return self.title
