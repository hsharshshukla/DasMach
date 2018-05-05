# make sure this is at the top if it isn't already
from django import forms
from buysale.models import car,newsletter,contact
# our new form
class vehicle(forms.ModelForm):
    owner_name = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Owner Name'}))
    owner_email = forms.EmailField(widget=forms.TextInput(attrs={'placeholder': 'Email ID'}))
    owner_mobile = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Contact No'}))
    class Meta:
        model = car
        fields = '__all__'

class contact(forms.ModelForm):
    contact_name = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Owner Name', 'size': '49'}))
    contact_email = forms.EmailField(widget=forms.TextInput(attrs={'placeholder': 'Email ID', 'size': '49'}))
    contact_mobile = forms.CharField(widget=forms.TextInput(attrs={'placeholder': 'Contact No', 'size': '49'}))
    contact_message = forms.CharField(widget=forms.Textarea(attrs={'placeholder': 'Your Message','rows':'7', 'cols':'48'}))
    class Meta:
        model = contact
        fields = '__all__'

class newsletter(forms.ModelForm):
    class Meta:
        model = newsletter
        fields = '__all__'